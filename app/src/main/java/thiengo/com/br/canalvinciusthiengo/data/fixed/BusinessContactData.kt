package thiengo.com.br.canalvinciusthiengo.data.fixed

import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.model.BusinessContact

/**
 * Contém os dados de contatos comerciais
 * vinculados ao canal YouTube do aplicativo.
 *
 * O objetivo desta classe é trabalhar como uma
 * persistência local estática, fixa, que contém
 * todos os dados de contatos comerciais.
 *
 * Como esses dados tendem a sofrer poucas
 * alterações (incluindo a inserção de novos contatos)
 * e com espaços de tempo longos entre as alterações,
 * então a melhor escolha foi o trabalho deles em
 * uma classe estática (companion object) que
 * trabalha como se fosse uma persistência de dados
 * estáticos.
 */
abstract class BusinessContactData {

    companion object{
        /**
         * Retorna todos os contatos comerciais do
         * canal.
         *
         * @return lista não mutável de objetos
         * BusinessContact.
         */
        fun getBusinessContacts()
            = listOf(
                BusinessContact(
                    place = "Gmail",
                    contact = "thiengocalopsita@gmail.com",
                    webUri = "mailto:thiengocalopsita@gmail.com?Subject=Contato comercial - Thiengo",
                    appUri = "",
                    logo = R.drawable.ic_gmail_color
                ),
                BusinessContact(
                    place = "Blog",
                    contact = "Thiengo.com.br",
                    webUri = "https://www.thiengo.com.br/contato",
                    appUri = "",
                    logo = R.drawable.ic_blog_color
                )/*,
                BusinessContact(
                    place = "WhatsApp",
                    contact = "+55 (27) 9-9999-9999",
                    webUri = "",
                    appUri = "",
                    logo = R.drawable.ic_whatsapp_color
                ),
                BusinessContact(
                    place = "Telegram",
                    contact = "+55 (27) 9-9999-9999",
                    webUri = "",
                    appUri = "",
                    logo = R.drawable.ic_telegram_color
                ),
                BusinessContact(
                    place = "Facebook Messeger",
                    contact = "Vinícius Thiengo",
                    webUri = "",
                    appUri = "",
                    logo = R.drawable.ic_facebook_messeger_color
                )*/
            )
    }
}