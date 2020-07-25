package thiengo.com.br.canalvinciusthiengo.domain

import android.content.res.Resources
import android.net.Uri

class SocialNetwork(
    val network: String,
    private val accountName: String,
    private val appUri: String,
    private val webUri: String,
    val logo: Int ) : ListItem{

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


    override fun getIcon()
        = logo

    override fun getMainText()
        = String.format(
            "%s: %s",
            network,
            accountName
        )

    override fun getFirstAuxText()
        = ""

    override fun getSecondAuxText(resource: Resources)
        = ""

    override fun getWebUri()
        = Uri.parse( webUri )

    override fun getAppUri()
        = Uri.parse( appUri )
}