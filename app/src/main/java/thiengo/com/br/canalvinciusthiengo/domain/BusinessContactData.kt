package thiengo.com.br.canalvinciusthiengo.domain

import thiengo.com.br.canalvinciusthiengo.R

class BusinessContactData {

    companion object{

        fun getBusinessContacts()
            = listOf(
                BusinessContact(
                    place = "Gmail",
                    contact = "thiengocalopsita@gmail.com",
                    appUri = "",
                    logo = R.drawable.ic_gmail_color
                ),
                BusinessContact(
                    place = "Blog",
                    contact = "Thiengo.com.br",
                    appUri = "",
                    logo = R.drawable.ic_blog_color
                ),
                BusinessContact(
                    place = "WhatsApp",
                    contact = "+55 (27) 9-9999-9999",
                    appUri = "",
                    logo = R.drawable.ic_whatsapp_color
                ),
                BusinessContact(
                    place = "Telegram",
                    contact = "+55 (27) 9-9999-9999",
                    appUri = "",
                    logo = R.drawable.ic_telegram_color
                ),
                BusinessContact(
                    place = "Facebook Messeger",
                    contact = "Vinícius Thiengo",
                    appUri = "",
                    logo = R.drawable.ic_facebook_messeger_color
                )
            )
    }
}