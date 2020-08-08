package thiengo.com.br.canalvinciusthiengo.ui.fragment

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_last_video.*
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.data.dynamic.NewLastVideoBroadcast
import thiengo.com.br.canalvinciusthiengo.data.dynamic.UtilDatabase
import thiengo.com.br.canalvinciusthiengo.data.fixed.LastVideoData
import thiengo.com.br.canalvinciusthiengo.model.LastVideo
import thiengo.com.br.canalvinciusthiengo.network.UtilNetwork

/**
 * Contém toda a UI de último vídeo disponível
 * no canal YouTube do app.
 *
 * @constructor cria um objeto completo do tipo
 * LastVideoFragment.
 */
class LastVideoFragment : Fragment() {

    companion object {
        /**
         * Constante com o identificador único do
         * fragmento LastVideoFragment para que
         * ele seja encontrado na pilha de fragmentos
         * e assim não seja necessária a construção
         * de mais de um objeto deste fragmento em
         * memória enquanto o aplicativo estiver em
         * execução.
         */
        const val KEY = "LastVideoFragment_key"
    }

    /**
     * [lastVideo] sempre inicia com algum dado válido
     * de "último vídeo" liberado, mesmo que nenhum
     * vídeo tenha sido ainda enviado ao app.
     */
    private var lastVideo: LastVideo = LastVideoData.getInitialVideo()

    private lateinit var localBroadcast: NewLastVideoBroadcast


    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        initLocalBroadcast()

        /**
         * Para garantir que o banco de dados local
         * será acessado apenas na primeira vez que
         * o fragmento é carregado.
         *
         * Sendo assim o usuário poderá mudar de tabs
         * (itens de menu) que não haverá novos
         * carregamentos somente devido à mudança de
         * tab. Isso se o objeto de fragmento for
         * retido em memória.
         */
        UtilDatabase
            .getInstance( context = activity!!.applicationContext )
            .getLastVideo{
                setUiModel( lVideo = it )
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {

        return inflater.inflate(
            R.layout.fragment_last_video,
            container,
            false
        )
    }

    override fun onActivityCreated( savedInstanceState: Bundle? ) {
        super.onActivityCreated( savedInstanceState )
        setListeners()
        setUiModel( lVideo = lastVideo )
    }

    override fun onDestroy(){
        super.onDestroy()
        destroyLocalBroadcast()
    }

    /**
     * Configura os listeners de swipe e de clique
     * de alguns componentes visuais em tela.
     */
    private fun setListeners(){
        srl_update_content?.setOnRefreshListener {
            swipeRefreshStatus( status = true )
            retrieveData()
        }
        ll_last_video_container?.setOnClickListener{
            openVideoOnYouTube()
        }
    }

    /**
     * Responsável pela configuração dos dados de
     * "último vídeo disponível" em tela.
     *
     * Como é possível que a invocação deste método
     * ocorra fora da Thread Principal, então é
     * importante sempre ter o código de atualização
     * de vídeo dentro de runOnUiThread().
     *
     * Outro ponto importante é garantir que não
     * haverá NullPointerException caso os dados
     * cheguem em método quando a UI não mais está no
     * foreground (primeiro plano). Assim o operador
     * not null (?.) é utilizado com frequência.
     *
     * @param lVideo último vídeo liberado em canal.
     */
    private fun setUiModel( lVideo: LastVideo? ){

        if( lVideo != null ){
            activity?.runOnUiThread{
                lastVideo = lVideo

                try{
                    Picasso
                        .get()
                        .load( lVideo.thumbUrl )
                        .into( iv_last_video_thumb )

                    iv_last_video_thumb?.contentDescription = lVideo.title
                }
                catch( e: Exception ){}

                tv_last_video_title?.text = lVideo.title
                descriptionStatus( description = lVideo.description )
            }
        }
    }

    /**
     * Garante que o componente visual de apresentação
     * de descrição do vídeo somente estará em tela
     * caso exista descrição (alguns vídeos não têm).
     *
     * @param description descrição do vídeo.
     */
    private fun descriptionStatus( description: String ){

        if( description.isNotEmpty() ){
            tv_last_video_desc?.text = description
            tv_last_video_desc?.visibility = View.VISIBLE
        }
        else{
            tv_last_video_desc?.visibility = View.GONE
        }
    }

    /**
     * Configura o estado atual do componente visual
     * SwipeRefresh.
     *
     * @param status estado atual do swipe.
     */
    private fun swipeRefreshStatus( status : Boolean ){
        activity?.runOnUiThread {
            srl_update_content?.isRefreshing = status
        }
    }

    /**
     * Solicita dados de último vídeo da fonte remota,
     * YouTube Data API.
     */
    private fun retrieveData(){
        UtilNetwork
            .getInstance( context = activity!! )
            .retrieveLastVideo(
                callbackSuccess = {
                    swipeRefreshStatus( status = false )
                    setUiModel( lVideo = it )
                },
                callbackFailure = {
                    swipeRefreshStatus( status = false )
                }
            )
    }

    /**
     * Invoca o aplicativo do YouTube para que o usuário
     * tenha acesso ao último vídeo liberado no canal.
     *
     * Caso o dado de URI presente no objeto [lastVideo] seja
     * inválido para a abertura do app nativo do YouTube
     * ou abertura da versão dele em app de navegador Web,
     * então uma mensagem de falha é apresentada.
     */
    private fun openVideoOnYouTube(){
        val intent = Intent(
            Intent.ACTION_VIEW,
            lastVideo.webUri()
        )

        /*
         * É utópico, mas pode ocorrer de não haver instalado
         * no aparelho do usuário o aplicativo do YouTube e
         * nem mesmo um navegador Web.
         *
         * Sendo assim, ao invés de gerar uma exceção, nós
         * avisamos ao usuário a necessidade de instalar o
         * aplicativo adequado.
         * */
        if( intent.resolveActivity( activity!!.packageManager ) == null ){
            Toast
                .makeText(
                    activity,
                    String.format(
                        getString( R.string.last_video_toast_alert ),
                        lastVideo.title
                    ),
                    Toast.LENGTH_LONG
                )
                .show()

            return
        }

        activity!!.startActivity( intent )
    }

    /**
     * Inicializa o LocalBroadcast para que seja possível
     * a atualização da UI de "último vídeo" liberado
     * quando o aplicativo está em foreground e um novo
     * "último vídeo" é recebido por ele.
     */
    private fun initLocalBroadcast(){
        val intentFilter = IntentFilter( NewLastVideoBroadcast.FILTER_KEY )

        localBroadcast = NewLastVideoBroadcast(
            fragment = this
        )

        LocalBroadcastManager
            .getInstance( activity!! )
            .registerReceiver(
                localBroadcast,
                intentFilter
            )
    }

    /**
     * Destrói o LocalBroadcast quando o aplicativo não
     * mais está em foreground. Pois não será possível
     * atualizar a UI neste caso.
     */
    private fun destroyLocalBroadcast(){
        LocalBroadcastManager
            .getInstance( activity!! )
            .unregisterReceiver( localBroadcast )
    }

    /**
     * Configura na propriedade [lastVideo] os dados do
     * último vídeo liberado em canal e recebidos em
     * aplicativo.
     *
     * @param video último vídeo liberado em canal.
     */
    fun newLastVideoData( video: LastVideo ){
        lastVideo = video
        setUiModel( lVideo =  video )
    }
}