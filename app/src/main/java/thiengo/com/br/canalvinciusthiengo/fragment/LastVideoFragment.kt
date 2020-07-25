package thiengo.com.br.canalvinciusthiengo.fragment

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
import thiengo.com.br.canalvinciusthiengo.data.NewLastVideoBroadcast
import thiengo.com.br.canalvinciusthiengo.data.UtilDatabase
import thiengo.com.br.canalvinciusthiengo.domain.LastVideo
import thiengo.com.br.canalvinciusthiengo.domain.LastVideoData
import thiengo.com.br.canalvinciusthiengo.network.UtilNetwork


class LastVideoFragment : Fragment() {

    companion object{
        const val KEY = "LastVideoFragment_key"
    }

    private var lastVideo: LastVideo = LastVideoData.getInitialVideo()
    private lateinit var broadcast: NewLastVideoBroadcast


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLocalBroadcast()

        /*
         * Para garantir que o banco de dados local será
         * acessado apenas na primeira vez que fragmento é
         * carregado.
         *
         * Sendo assim o usuário poderá mudar de tabs que não
         * haverá novos carregamentos somente devido à mudança
         * de tab.
         * */
        UtilDatabase
            .getInstance( context = activity!!.applicationContext )
            .getLastVideo{
                setUiModel( video = it )
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
        setUiModel( video = lastVideo )
    }

    override fun onDestroy(){
        super.onDestroy()
        destroyLocalBroadcast()
    }

    private fun setListeners(){
        srl_update_content?.setOnRefreshListener {
            swipeRefreshStatus( status = true )
            retrieveData()
        }
        ll_last_video_container?.setOnClickListener{
            openVideoOnYouTube()
        }
    }

    private fun setUiModel( video: LastVideo? ){
        if( video != null ){
            activity?.runOnUiThread{
                lastVideo = video

                try{
                    Picasso
                        .get()
                        .load( video.correctThumbUr() )
                        .into( iv_last_video_thumb )

                    iv_last_video_thumb?.contentDescription = video.title
                }
                catch( e: Exception ){}

                tv_last_video_title?.text = video.title
                descriptionStatus( description = video.description )
            }
        }
    }

    private fun descriptionStatus( description: String ){
        if( description.isNotEmpty() ){
            tv_last_video_desc?.text = description
            tv_last_video_desc?.visibility = View.VISIBLE
        }
        else{
            tv_last_video_desc?.visibility = View.GONE
        }
    }

    private fun swipeRefreshStatus( status : Boolean ){
        activity?.runOnUiThread {
            srl_update_content?.isRefreshing = status
        }
    }

    private fun retrieveData(){
        UtilNetwork
            .getInstance( context = activity!! )
            .retrieveLastVideo(
                callbackSuccess = {
                    swipeRefreshStatus( status = false )
                    setUiModel( video = it )
                },
                callbackFailure = {
                    swipeRefreshStatus( status = false )
                }
            )
    }

    private fun openVideoOnYouTube(){
        val intent = Intent(
            Intent.ACTION_VIEW,
            lastVideo.appUri()
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


    /* LocalBroadcast */
    private fun initLocalBroadcast(){
        val intentFilter = IntentFilter( NewLastVideoBroadcast.FILTER_KEY )
        broadcast = NewLastVideoBroadcast( fragment = this )

        LocalBroadcastManager
            .getInstance( activity!! )
            .registerReceiver( broadcast, intentFilter )
    }

    private fun destroyLocalBroadcast(){
        LocalBroadcastManager
            .getInstance( activity!! )
            .unregisterReceiver( broadcast )
    }

    fun newLastVideoData( lVideo: LastVideo ){
        lastVideo = lVideo
        setUiModel( lastVideo )
    }
}