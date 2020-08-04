package thiengo.com.br.canalvinciusthiengo.data.fixed

import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.model.Group

/**
 * Contém os dados dos grupos, de outras redes,
 * vinculados ao canal YouTube do aplicativo.
 *
 * O objetivo desta classe é trabalhar como uma
 * persistência local estática, fixa, que contém
 * os dados dos grupos.
 *
 * Como esses dados tendem a sofrer poucas
 * alterações (incluindo a inserção de novos grupos)
 * e com espaços de tempo longos entre as alterações,
 * então a melhor escolha foi o trabalho deles em
 * uma classe estática (companion object) que
 * trabalha como se fosse uma persistência de dados
 * estáticos.
 */
abstract class GroupsData {

    companion object{
        /**
         * Retorna todos os grupos vinculados ao
         * canal.
         *
         * @return lista não mutável de objetos
         * Group.
         */
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