package thiengo.com.br.canalvinciusthiengo.data.fixed

import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.model.SocialNetwork

/**
 * Contém os dados das outras redes (sociais ou sites),
 * vinculados ao canal YouTube do aplicativo.
 *
 * O objetivo desta classe é trabalhar como uma
 * persistência local estática, fixa, que contém
 * os dados dessas outras redes.
 *
 * Como esses dados tendem a sofrer poucas
 * alterações (incluindo a inserção de novas redes)
 * e com espaços de tempo longos entre as alterações,
 * então a melhor escolha foi o trabalho deles em
 * uma classe estática (companion object) que
 * trabalha como se fosse uma persistência de dados
 * estáticos.
 */
abstract class SocialNetworksData {

    companion object{
        /**
         * Retorna todas as redes vinculadas ao
         * canal.
         *
         * @return lista não mutável de objetos
         * [SocialNetwork].
         */
        fun getNetworks()
            = listOf(
                SocialNetwork(
                    network = "Blog",
                    accountName = "Thiengo.com.br",
                    appUri = "",
                    webUri = "https://www.thiengo.com.br",
                    logo = R.drawable.ic_blog_color
                ),
                SocialNetwork(
                    network = "Github",
                    accountName = "/viniciusthiengo",
                    appUri = "",
                    webUri = "https://github.com/viniciusthiengo",
                    logo = R.drawable.ic_github_color
                ),
                SocialNetwork(
                    network = "LinkedIn",
                    accountName = "/vinícius-thiengo",
                    appUri = "linkedin://profile/vinícius-thiengo-5179b180",
                    webUri = "https://www.linkedin.com/in/vin%C3%ADcius-thiengo-5179b180",
                    logo = R.drawable.ic_linkedin_color
                ),
                SocialNetwork(
                    network = "Udemy",
                    accountName = "/vinícius-thiengo",
                    appUri = "",
                    webUri = "https://www.udemy.com/user/vinicius-thiengo/?persist_locale&locale=pt_BR",
                    logo = R.drawable.ic_udemy_color
                ),
                SocialNetwork(
                    network = "Facebook",
                    accountName = "/thiengoCalopsita",
                    appUri = "",
                    webUri = "https://www.facebook.com/thiengoCalopsita",
                    logo = R.drawable.ic_facebook_color
                ),
                SocialNetwork(
                    network = "SlideShare",
                    accountName = "/VinciusThiengo",
                    appUri = "",
                    webUri = "https://www.slideshare.net/VinciusThiengo",
                    logo = R.drawable.ic_slide_share_color
                ),
                SocialNetwork(
                    network = "Twitter",
                    accountName = "/thiengoCalops",
                    appUri = "",
                    webUri = "https://twitter.com/thiengoCalops",
                    logo = R.drawable.ic_twitter_color
                ),
                SocialNetwork(
                    network = "YouTube",
                    accountName = "/ThiengoCalopsita",
                    appUri = "",
                    webUri = "https://www.youtube.com/user/thiengoCalopsita",
                    logo = R.drawable.ic_youtube_color
                )
            )
    }
}