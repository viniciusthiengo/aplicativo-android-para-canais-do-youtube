package thiengo.com.br.canalvinciusthiengo.domain

import android.content.res.Resources
import android.net.Uri

class Group(
    val place: String,
    val name: String,
    private val appUri: String,
    val logo: Int ) : ListItem {

    fun labelToItem()
        = String.format(
            "%s: %s",
            place,
            name
        )

    fun appUri()
        = Uri.parse( appUri )

    override fun getIcon()
        = logo

    override fun getMainText()
        = String.format(
            "%s: %s",
            place,
            name
        )

    override fun getFirstAuxText()
        = ""

    override fun getSecondAuxText(resource: Resources)
        = ""

    override fun getWebUri()
        = null

    override fun getAppUri()
        = Uri.parse( appUri )
}