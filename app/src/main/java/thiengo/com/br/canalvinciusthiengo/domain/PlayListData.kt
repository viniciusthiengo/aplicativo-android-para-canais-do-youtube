package thiengo.com.br.canalvinciusthiengo.domain

class PlayListData {

    companion object{

        fun getInitialPlayLists()
            //= mutableListOf<PlayList>()

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