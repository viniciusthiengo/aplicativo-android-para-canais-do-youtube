package thiengo.com.br.canalvinciusthiengo.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import thiengo.com.br.canalvinciusthiengo.config.YouTubeConfig
import thiengo.com.br.canalvinciusthiengo.network.playlist.PlayLists
import thiengo.com.br.canalvinciusthiengo.network.video.Video

interface YouTubeService {

    @GET( "youtube/v3/search" )
    fun lastVideo(
        @Query("channelId") channelId: String = YouTubeConfig.CHANNEL_ID.value,
        @Query("part") part: String = YouTubeConfig.API_SNIPPET_PARAMETER.value,
        @Query("maxResults") maxResults: Int = 1,
        @Query("order") order: String = YouTubeConfig.API_ORDER_PARAMETER.value,
        @Query("key") key: String = YouTubeConfig.GOOGLE_DEV_KEY.value
    ): Call<Video>

    @GET("youtube/v3/playlists")
    fun playLists(
        @Query("channelId") channelId: String = YouTubeConfig.CHANNEL_ID.value,
        @Query("part") part: String = YouTubeConfig.API_SNIPPET_PARAMETER.value,
        @Query("maxResults") maxResults: String = YouTubeConfig.API_MAX_RESULTS_PLAYLISTS_PARAMETER.value,
        @Query("order") order: String = YouTubeConfig.API_ORDER_PARAMETER.value,
        @Query("key") key: String = YouTubeConfig.GOOGLE_DEV_KEY.value
    ): Call<PlayLists>
}