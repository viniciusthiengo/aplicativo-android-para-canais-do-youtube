package thiengo.com.br.canalvinciusthiengo.domain

import android.net.Uri

class BusinessContact(
        val place: String,
        val contact: String,
        private val appUri: String,
        val logo: Int
    ) {

    fun labelToItem()
        = String.format(
            "%s: %s",
            place,
            contact
        )

    fun appUri()
        = Uri.parse( appUri )
}