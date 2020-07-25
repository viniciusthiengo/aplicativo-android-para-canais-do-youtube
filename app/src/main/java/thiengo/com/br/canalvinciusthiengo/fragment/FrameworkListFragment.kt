package thiengo.com.br.canalvinciusthiengo.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_framework_list.*
import thiengo.com.br.canalvinciusthiengo.R


abstract class FrameworkListFragment : Fragment() {

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

    protected fun setUiModel(
        titleText: String,
        subTitleText: String = "" ){

        tv_main_text.text = titleText
        //(tv_title.layoutParams as LinearLayout.LayoutParams).bottomMargin = convertDpToPixels( 5 )

        tv_sub_title.text = subTitleText
        tv_sub_title.visibility = if( subTitleText.isEmpty() ){
                View.GONE
            }
            else{
                View.VISIBLE
            }
    }

    /*fun convertDpToPixels( dp: Int ) : Int {
        val scale = resources.displayMetrics.density
        val gestureThresholdDP = resources.displayMetrics.density

        return (gestureThresholdDP * scale + 0.5f).toInt()
    }*/

    protected fun <T : RecyclerView.ViewHolder> initList(
        adapter: RecyclerView.Adapter<T> ){

        val layoutManager = LinearLayoutManager( activity )
        rv_items.layoutManager = layoutManager

        rv_items.setHasFixedSize( true )
        rv_items.adapter = adapter
    }

    protected fun callExternalApp(
        webUri: Uri? = null,
        appUri: Uri? = null,
        failMessage: String ){


        val intentApp = getIntentUri( uri = appUri )
        val intentWeb = getIntentUri( uri = webUri )
        var intent: Intent? = null


        /*
         * Caso não tenha no aparelho o aplicativo oficial da rede
         * acionada, então cria a configuração para tentar abrir a
         * rede pelo navegador padrão do aparelho.
         * */
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
            /*
             * É utópico, mas pode ocorrer de não haver nem mesmo um
             * navegador Web no aparelho do usuário que abra a URI da
             * rede acionada.
             *
             * Sendo assim, ao invés de gerar uma exceção, nós
             * avisamos ao usuário a necessidade de instalar o
             * aplicativo adequado.
             * */

            Toast
                .makeText(
                    activity,
                    failMessage,
                    Toast.LENGTH_LONG
                )
                .show()
        }
    }

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