package thiengo.com.br.canalvinciusthiengo.network

/**
 * Contém os possíveis problemas que podem ocorrer
 * em comunicação remota e que serão trabalhados pelo
 * aplicativo.
 */
enum class NetworkRetrieveDataProblem {
    NO_VIDEO,
    NO_PLAYLISTS,
    NO_INTERNET_CONNECTION
}