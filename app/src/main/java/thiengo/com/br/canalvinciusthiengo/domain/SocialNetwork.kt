package thiengo.com.br.canalvinciusthiengo.domain

import android.net.Uri

class SocialNetwork(
        val network: String,
        private val accountName: String,
        private val appUri: String,
        private val webUri: String,
        val logo: Int
    ) {

    fun labelToItem()
        = String.format(
            "%s: %s",
            network,
            accountName
        )

    fun appUri()
        = Uri.parse( appUri )

    fun webUri()
        = Uri.parse( webUri )
}