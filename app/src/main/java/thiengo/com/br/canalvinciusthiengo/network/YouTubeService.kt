package thiengo.com.br.canalvinciusthiengo.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import thiengo.com.br.canalvinciusthiengo.config.YouTubeConfig
import thiengo.com.br.canalvinciusthiengo.model.parse.playlist.PlayListsParse
import thiengo.com.br.canalvinciusthiengo.model.parse.video.VideoParse

/**
 * Interface responsável por definir a API de comunicação
 * remota do aplicativo para ser utilziada junto à
 * biblioteca Retrofit.
 */
interface YouTubeService {

    /**
     * Invoca dados de "último vídeo" disponível de acordo
     * com os parâmetros query informados.
     *
     * @param key chave de API de desenvolvedor Google.
     * @param channelId identificador único do canal.
     * @param part trecho de dados que deve estar presente
     * em recurso vídeo retornado.
     * @param maxResults número máximo de videos.
     * @param order dado de ordenação dos resultados.
     * @return todos os dados da resposta do servidor
     * remoto.
     */
    @GET( value = YouTubeConfig.ApiV3.VIDEO_PATH )
    fun lastVideo(
        @Query("key")
        key: String = YouTubeConfig.Key.GOOGLE_DEV,
        @Query("channelId")
        channelId: String = YouTubeConfig.Channel.CHANNEL_ID,
        @Query("part")
        part: String = YouTubeConfig.ApiV3.PART_PARAM,
        @Query("maxResults")
        maxResults: String = YouTubeConfig.ApiV3.MAX_RESULTS_VIDEO_PARAM,
        @Query("order")
        order: String = YouTubeConfig.ApiV3.ORDER_PARAM
    ): Call<VideoParse>

    /**
     * Invoca dados de PlayLists disponíveis de acordo
     * com os parâmetros query informados.
     *
     * @param key chave de API de desenvolvedor Google.
     * @param channelId identificador único do canal.
     * @param part trecho de dados que deve estar presente
     * em recurso vídeo retornado.
     * @param maxResults número máximo de PlayLists.
     * @param order dado de ordenação dos resultados.
     * @return todos os dados da resposta do servidor
     * remoto.
     */
    @GET( value = YouTubeConfig.ApiV3.PLAYLISTS_PATH )
    fun playLists(
        @Query("key")
        key: String = YouTubeConfig.Key.GOOGLE_DEV,
        @Query("channelId")
        channelId: String = YouTubeConfig.Channel.CHANNEL_ID,
        @Query("part")
        part: String = YouTubeConfig.ApiV3.PART_PARAM,
        @Query("maxResults")
        maxResults: String = YouTubeConfig.ApiV3.MAX_RESULTS_PLAYLISTS_PARAM,
        @Query("order")
        order: String = YouTubeConfig.ApiV3.ORDER_PARAM
    ): Call<PlayListsParse>
}