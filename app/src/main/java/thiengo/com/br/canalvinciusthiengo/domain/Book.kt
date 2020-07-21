package thiengo.com.br.canalvinciusthiengo.domain

import android.net.Uri

class Book(
        val title: String,
        val categories: List<String>,
        private val webPage: String
    ) {

    fun categoriesAsItemLabel()
        = categories.joinToString(", ")

    fun webPageUri()
        = Uri.parse( webPage )
}