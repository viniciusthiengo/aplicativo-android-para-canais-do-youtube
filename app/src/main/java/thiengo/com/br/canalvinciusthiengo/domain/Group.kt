package thiengo.com.br.canalvinciusthiengo.domain

import android.net.Uri

class Group(
        val place: String,
        val name: String,
        private val appUri: String,
        val logo: Int
    ) {

    fun labelToItem()
        = String.format(
            "%s: %s",
            place,
            name
        )

    fun appUri()
        = Uri.parse( appUri )
}