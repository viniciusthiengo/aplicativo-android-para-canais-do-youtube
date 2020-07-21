package thiengo.com.br.canalvinciusthiengo.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_social_networks.*
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.adapter.SocialNetworkAdapter
import thiengo.com.br.canalvinciusthiengo.domain.SocialNetwork
import thiengo.com.br.canalvinciusthiengo.domain.SocialNetworksData


class SocialNetworksFragment : Fragment() {

    companion object{
        const val KEY = "SocialNetworksFragment_key"
    }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

        return inflater.inflate(
            R.layout.fragment_social_networks,
            container,
            false
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated( savedInstanceState )

        initNetworkList()
    }

    private fun initNetworkList(){

        val layoutManager = LinearLayoutManager( activity )
        rv_social_networks.layoutManager = layoutManager

        rv_social_networks.setHasFixedSize( true )
        rv_social_networks.adapter = SocialNetworkAdapter(
            context = activity!!,
            networks = SocialNetworksData.getNetworks(),
            callNetworkAppCallback = {
                network -> callNetworkAppCallback( network )
            }
        )
    }

    private fun callNetworkAppCallback( network: SocialNetwork ){

        var intent = Intent(
            Intent.ACTION_VIEW,
            network.appUri()
        )

        /*
         * Caso não tenha no aparelho o aplicativo oficial da rede
         * acionada, então cria a configuração para tentar abrir a
         * rede pelo navegador padrão do aparelho.
         * */
        if( intent.resolveActivity( activity!!.packageManager ) == null ){
            intent = Intent(
                Intent.ACTION_VIEW,
                network.webUri()
            )
        }

        /*
         * É utópico, mas pode ocorrer de não haver nem mesmo um
         * navegador Web no aparelho do usuário que abra a URI da
         * rede acionada.
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
                        getString( R.string.social_network_toast_alert ),
                        network.network
                    ),
                    Toast.LENGTH_LONG
                )
                .show()

            return
        }

        activity!!.startActivity( intent )
    }
}