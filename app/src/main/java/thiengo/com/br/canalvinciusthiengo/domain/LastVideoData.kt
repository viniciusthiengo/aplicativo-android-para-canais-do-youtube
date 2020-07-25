package thiengo.com.br.canalvinciusthiengo.domain

class LastVideoData {
    companion object{
        fun getInitialVideo()
            = LastVideo(
                uid = "g8h8QkSPMQE",
                title = "[MINI-CURSO] Porque e Como Utilizar Vetores no Android - PARTE 6",
                description = "O vídeo acima é a quinta vídeo aula de um mini-curso completo sobre Drawables Vetoriais no desenvolvimento de aplicativos Android."
            ).apply {
                thumbUrl = ""
            }
    }
}