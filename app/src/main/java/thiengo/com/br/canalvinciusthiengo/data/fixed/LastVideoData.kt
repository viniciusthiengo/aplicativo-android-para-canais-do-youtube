package thiengo.com.br.canalvinciusthiengo.data.fixed

import thiengo.com.br.canalvinciusthiengo.model.LastVideo

/**
 * Contém o vídeo inicial que deve ser carregado
 * junto ao aplicativo enquanto um vídeo mais
 * atual não é enviado (ou acessado) a ele.
 *
 * O objetivo desta classe é trabalhar como uma
 * persistência local estática, fixa, que contém
 * os dados de algum vídeo do canal YouTube do
 * app. Assim o usuário sempre terá algum
 * "último" vídeo disponível para acesso, mesmo
 * quando ainda não foi retornado (ou acessado)
 * os dados do vídeo mais atual já disponível
 * no canal.
 */
class LastVideoData {

    companion object{
        /**
         * Retorna o "último" vídeo disponível
         * por padrão no aplicativo
         *
         * @return objeto do tipo LastVideo.
         */
        fun getInitialVideo()
            = LastVideo(
                uid = "g8h8QkSPMQE",
                title = "[MINI-CURSO] Porque e Como Utilizar " +
                    "Vetores no Android - PARTE 6",
                description = "O vídeo acima é a quinta vídeo aula " +
                    "de um mini-curso completo sobre Drawables " +
                    "Vetoriais no desenvolvimento de aplicativos " +
                    "Android."
            ).apply {
                thumbUrl = ""
            }
    }
}