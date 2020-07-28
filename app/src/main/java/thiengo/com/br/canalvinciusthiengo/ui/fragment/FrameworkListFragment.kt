package thiengo.com.br.canalvinciusthiengo.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_framework_list.*
import thiengo.com.br.canalvinciusthiengo.R

/**
 * Classe base para todos os fragmentos do projeto
 * que têm como parte principal do layout o framework
 * de lista RecyclerView e os dados estão somente
 * local em projeto (data/fixed).
 */
abstract class FrameworkListFragment : Fragment() {

    companion object {
        /**
         * Constante com o valor padrão em DPs que
         * deve ser aplicado na BottomMargin do
         * título principal do fragmento em relação
         * ao sub-título, isso somente se existir
         * algum sub-título.
         */
        private const val MARGIN_TO_SUB_TITLE = 5
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {

        return inflater.inflate(
            R.layout.fragment_framework_list,
            container,
            false
        )
    }

    /**
     * Configuração dos componentes de UI extra
     * framework de lista e que estão no mesmo
     * layout de fragmentos com lista de itens.
     *
     * @param titleText título principal em tela.
     * @param subTitleText sub-título (descrição
     * mais detalhada do conteúdo).
     */
    protected fun setUiModel(
        titleText: String,
        subTitleText: String = "" ){

        tv_main_text.text = titleText
        tv_sub_title.text = subTitleText
        tv_sub_title.visibility = if( subTitleText.isEmpty() ){
                View.GONE
            }
            else{
                updateMainTextMargin()
                View.VISIBLE
            }
    }

    /**
     * Atualiza o valor de bottomMargin do título do
     * fragmento quando também há em layout um outro
     * TextView de conteúdo extra além do TextView de
     * título e do RecyclerView de itens de lista.
     *
     * Desta forma o conteúdo fica mais apresentável.
     *
     * O valor em bottomMargin precisa ser em pixels,
     * por isso a necessidade de conversão de DPs para
     * pixels.
     */
    private fun updateMainTextMargin(){
        val tvTitleLayoutParams = (tv_main_text.layoutParams as LinearLayout.LayoutParams)
        tvTitleLayoutParams.bottomMargin = convertDpToPixels()
    }

    /**
     * Converte um número inteiro [MARGIN_TO_SUB_TITLE]
     * em DPs para a sua versão em pixels.
     *
     * @return valor em pixels do valor em DP informado
     * em [MARGIN_TO_SUB_TITLE].
     */
    private fun convertDpToPixels() : Int {
        val scale = resources.displayMetrics.density
        return (MARGIN_TO_SUB_TITLE * scale + 0.5f).toInt()
    }

    /**
     * Permite que todos os fragmentos filhos utilizem um
     * mesmo código de inicialização de framework de lista.
     *
     * @param adapter adaptador de item de lista
     * adequadamente configurado de acordo com o fragmento
     * que o utiliza.
     */
    protected fun <T : RecyclerView.ViewHolder> initList(
        adapter: RecyclerView.Adapter<T> ){

        val layoutManager = LinearLayoutManager( activity )
        rv_items.layoutManager = layoutManager

        rv_items.setHasFixedSize( true )
        rv_items.adapter = adapter
    }

    /**
     * Invoca um aplicativo auxiliar para que o usuário
     * tenha acesso a outros conteúdos do canal que estão
     * disponíveis em outras redes (apps ou sites).
     *
     * Caso os dados de URI fornecidos sejam inválidos,
     * então uma mensagem de falha é apresentada.
     *
     * @param webUri URL para abertura de app auxiliar
     * ou navegador Web.
     * @param appUri endereço para abertura de app nativo
     * auxiliar.
     * @param failMessage mensagem de falha caso as URIs
     * sejam inválidas.
     */
    protected fun callExternalApp(
        webUri: Uri? = null,
        appUri: Uri? = null,
        failMessage: String ){

        val intentApp = getIntentUri( uri = appUri )
        val intentWeb = getIntentUri( uri = webUri )
        var intent: Intent? = null

        /**
         * Caso não tenha no aparelho o aplicativo oficial
         * da rede acionada, então cria a configuração
         * para tentar abrir a rede pelo navegador Web
         * padrão do device.
         */
        if( intentApp != null
            && intentApp.resolveActivity( activity!!.packageManager ) != null ){

            intent = intentApp
        }
        else if( intentWeb != null
            && intentWeb.resolveActivity( activity!!.packageManager ) != null ){

            intent = intentWeb
        }

        if( intent != null ){
            activity!!.startActivity( intent )
        }
        else{
            /**
             * É utópico, mas pode ocorrer de não haver
             * nem mesmo um navegador Web no aparelho do
             * usuário que abra a URL (se houver algo em
             * webUri) da rede acionada.
             *
             * Sendo assim, ao invés de gerar uma exceção,
             * nós avisamos ao usuário a necessidade de
             * instalar o aplicativo adequado.
             */
            Toast
                .makeText(
                    activity,
                    failMessage,
                    Toast.LENGTH_LONG
                )
                .show()
        }
    }

    /**
     * Retorna um objeto Intent válido ou null de acordo
     * com o valor presente em uri.
     *
     * @param uri endereço para abertura de app auxiliar.
     * @return intent com configuração de abertura de
     * app auxiliar.
     */
    private fun getIntentUri( uri: Uri? ) : Intent?
        = if( uri != null ){
            Intent(
                Intent.ACTION_VIEW,
                uri
            )
        }
        else{
            null
        }
}