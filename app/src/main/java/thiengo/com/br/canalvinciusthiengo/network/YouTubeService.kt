package thiengo.com.br.canalvinciusthiengo.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import thiengo.com.br.canalvinciusthiengo.config.YouTubeConfig
import thiengo.com.br.canalvinciusthiengo.network.playlistparse.PlayListsParse
import thiengo.com.br.canalvinciusthiengo.network.videoparse.VideoParse

interface YouTubeService {

    @GET( value = YouTubeConfig.ApiV3.VIDEO_PATH )
    fun lastVideo(
        @Query("channelId") channelId: String = YouTubeConfig.Channel.CHANNEL_ID,
        @Query("part") part: String = YouTubeConfig.ApiV3.PART_PARAM,
        @Query("maxResults") maxResults: String = YouTubeConfig.ApiV3.MAX_RESULTS_VIDEO_PARAM,
        @Query("order") order: String = YouTubeConfig.ApiV3.ORDER_PARAM,
        @Query("key") key: String = YouTubeConfig.Key.GOOGLE_DEV
    ): Call<VideoParse>

    @GET( value = YouTubeConfig.ApiV3.PLAYLISTS_PATH )
    fun playLists(
        @Query("channelId") channelId: String = YouTubeConfig.Channel.CHANNEL_ID,
        @Query("part") part: String = YouTubeConfig.ApiV3.PART_PARAM,
        @Query("maxResults") maxResults: String = YouTubeConfig.ApiV3.MAX_RESULTS_PLAYLISTS_PARAM,
        @Query("order") order: String = YouTubeConfig.ApiV3.ORDER_PARAM,
        @Query("key") key: String = YouTubeConfig.Key.GOOGLE_DEV
    ): Call<PlayListsParse>
}