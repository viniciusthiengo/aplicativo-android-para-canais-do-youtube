package thiengo.com.br.canalvinciusthiengo.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import thiengo.com.br.canalvinciusthiengo.network.playlist.PlayLists
import thiengo.com.br.canalvinciusthiengo.network.video.Video


// Last Vídeo
// https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=UCG3gFuIkRF3PpNkRk3Wp6dw&maxResults=1&order=date&key=AIzaSyA3PIJs6i88pdEjmVldLnsR7FeflPv6MJs

// PlayLIsts
// https://www.googleapis.com/youtube/v3/playlists?part=snippet&maxResults=200&channelId=UCG3gFuIkRF3PpNkRk3Wp6dw&order=date&key=AIzaSyA3PIJs6i88pdEjmVldLnsR7FeflPv6MJs


interface YouTubeService {

    @GET("youtube/v3/search")
    fun lastVideo(
        @Query("channelId") channelId: String = UtilNetwork.CHANNEL_ID,
        @Query("part") part: String = "snippet",
        @Query("maxResults") maxResults: Int = 1,
        @Query("order") order: String = "date",
        @Query("key") key: String = UtilNetwork.GOOGLE_DEV_KEY
    ): Call<Video>

    @GET("youtube/v3/playlists")
    fun playLists(
        @Query("channelId") channelId: String = UtilNetwork.CHANNEL_ID,
        @Query("part") part: String = "snippet",
        @Query("maxResults") maxResults: Int = 500,
        @Query("order") order: String = "date",
        @Query("key") key: String = UtilNetwork.GOOGLE_DEV_KEY
    ): Call<PlayLists>
}