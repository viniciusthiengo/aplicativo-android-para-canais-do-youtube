package thiengo.com.br.canalvinciusthiengo.domain

import thiengo.com.br.canalvinciusthiengo.R

class GroupsData {

    companion object{

        fun getGroups()
            = listOf(
                Group(
                    place = "Facebook",
                    name = "Seguidores do Blog Thiengo.com.br",
                    appUri = "",
                    logo = R.drawable.ic_facebook_group_color
                ),
                Group(
                    place = "Facebook",
                    name = "Leitores dos livros do Thiengo",
                    appUri = "",
                    logo = R.drawable.ic_facebook_group_color
                ),
                Group(
                    place = "WhatsApp",
                    name = "[Thiengo] Devs Android",
                    appUri = "",
                    logo = R.drawable.ic_whatsapp_color
                ),
                Group(
                    place = "Telegram",
                    name = "[Thiengo] Devs Android",
                    appUri = "",
                    logo = R.drawable.ic_telegram_color
                )
            )
    }
}