package thiengo.com.br.canalvinciusthiengo.domain

import android.content.res.Resources
import android.net.Uri
import thiengo.com.br.canalvinciusthiengo.R

class Book(
    val title: String,
    val categories: List<String>,
    private val webPage: String ) : ListItem {

    fun categoriesAsItemLabel()
        = categories.joinToString(", ")

    fun webPageUri()
        = Uri.parse( webPage )

    override fun getIcon()
        = R.drawable.ic_books_color

    override fun getMainText()
        = title

    override fun getFirstAuxText()
        = categories.joinToString(", ")

    override fun getSecondAuxText( resource: Resources )
        = ""

    override fun getWebUri()
        = Uri.parse( webPage )

    override fun getAppUri()
        = null
}