package thiengo.com.br.canalvinciusthiengo.fragment

import android.os.Bundle
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.adapter.ListItemAdapter
import thiengo.com.br.canalvinciusthiengo.adapter.SocialNetworkAdapter
import thiengo.com.br.canalvinciusthiengo.domain.CoursesData
import thiengo.com.br.canalvinciusthiengo.domain.SocialNetwork
import thiengo.com.br.canalvinciusthiengo.domain.SocialNetworksData


class SocialNetworksFragment : FrameworkListFragment() {

    companion object{
        const val KEY = "SocialNetworksFragment_key"
    }

    override fun onActivityCreated( savedInstanceState: Bundle? ) {
        super.onActivityCreated( savedInstanceState )

        setUiModel(
            titleText = getString( R.string.social_networks_content_title )
        )

        val adapter = ListItemAdapter(
            context = activity!!,
            items = SocialNetworksData.getNetworks(),
            callExternalAppCallback = {
                item -> callExternalApp(
                    webUri = item.getWebUri(),
                    appUri = item.getAppUri(),
                    failMessage = String.format(
                        getString( R.string.social_network_toast_alert ),
                        (item as SocialNetwork).network
                    )
                )
            }
        )
        initList( adapter = adapter )

        /*val adapter = SocialNetworkAdapter(
            context = activity!!,
            networks = SocialNetworksData.getNetworks(),
            callNetworkAppCallback = {
                network -> callExternalApp(
                    webUri = network.webUri(),
                    appUri = network.appUri(),
                    failMessage = String.format(
                        getString( R.string.social_network_toast_alert ),
                        network.network
                    )
                )
            }
        )
        initList( adapter = adapter )*/
    }
}