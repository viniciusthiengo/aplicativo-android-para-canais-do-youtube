package thiengo.com.br.canalvinciusthiengo.data.fixed

import thiengo.com.br.canalvinciusthiengo.model.PlayList

/**
 * Contém as principais PlayLists vinculadas ao
 * canal YouTube do aplicativo.
 *
 * O objetivo desta classe é trabalhar como uma
 * persistência local estática, fixa, que contém
 * os dados das principais PlayLists do canal.
 *
 * Pois devido às limitações da YouTube Data API
 * é importante ter também em app os dados das
 * principais PlayLists e assim ter certeza que
 * o usuário terá acesso a elas.
 */
class PlayListData {

    companion object{
        /**
         * Retorna as principais PlayLists
         * vinculadas ao canal.
         *
         * @return lista mutável de objetos
         * PlayList.
         */
        fun getInitialPlayLists()
            // = mutableListOf<PlayList>()
            = mutableListOf(
                PlayList(
                    title = "[MINI-CURSO] Porque e Como Utilizar Vetores no Android",
                    uid = "PLBA57K2L2RIJeKoaLgTtYKSFAhvH6FAcG"
                ),
                PlayList(
                    title = "Como Desenvolver a Tela de Listagem de Calçados - Android M-Commerce",
                    uid = "PLBA57K2L2RII2XZc79MqnqeuhG6VqfYjM"
                ),
                PlayList(
                    title = "Como Melhorar a Área de Configurações de Conta - Android M-Commerce",
                    uid = "PLBA57K2L2RIKwFMT9IU06wgFDlMW6WHJo"
                ),
                PlayList(
                    title = "Como Desenvolver as Telas de Endereço de Entrega - Android M-Commerce",
                    uid = "PLBA57K2L2RIJ7uLasfzwBGiip4fcVH1oS"
                ),
                PlayList(
                    title = "Desenvolvendo as Telas de Cartão de Crédito Com Máscara de Campo - Android M-Commerce",
                    uid = "PLBA57K2L2RILKKBEGsUk039no5sakLVOS"
                ),
                PlayList(
                    title = "Como Desenvolver as Telas de Configuração de E-mail e Senha - Android M-Commerce",
                    uid = "PLBA57K2L2RIKHvU3LxgnSIzFxYZ5wtR2W"
                )
            )
    }
}