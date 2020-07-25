package thiengo.com.br.canalvinciusthiengo.config

// Last Vídeo
// https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=UCG3gFuIkRF3PpNkRk3Wp6dw&maxResults=1&order=date&key=AIzaSyA3PIJs6i88pdEjmVldLnsR7FeflPv6MJs

// PlayLIsts
// https://www.googleapis.com/youtube/v3/playlists?part=snippet&maxResults=200&channelId=UCG3gFuIkRF3PpNkRk3Wp6dw&order=date&key=AIzaSyA3PIJs6i88pdEjmVldLnsR7FeflPv6MJs


class YouTubeConfig {

    class Key{
        companion object{
            const val GOOGLE_DEV = "" // Thiengo channel (BirdingBox)
            //const val GOOGLE_DEV = "" // Thiengo channel (Cursos)
        }
    }

    class Channel{
        companion object{
            const val CHANNEL_ID = "" // Thiengo channel
            //const val CHANNEL_ID = "UCglvzYlkZgttz63tUiaqooA" // Somebody else channel

            const val VIDEO_URL_TEMPLATE = "https://www.youtube.com/watch?v=%s"
            const val VIDEO_THUMB_URL_TEMPLATE = "https://i.ytimg.com/vi/%s/hqdefault.jpg"
            const val PLAYLIST_URL_TEMPLATE = "https://www.youtube.com/playlist?list=%s"
        }
    }

    class ApiV3{
        companion object{
            const val BASE_URL = "https://www.googleapis.com/"

            const val VIDEO_PATH = "youtube/v3/search"
            const val PLAYLISTS_PATH = "youtube/v3/playlists"

            const val PART_PARAM = "snippet"
            const val MAX_RESULTS_VIDEO_PARAM = "1"
            const val MAX_RESULTS_PLAYLISTS_PARAM = "500"
            const val ORDER_PARAM = "date"
        }
    }

    class Notification{
        companion object{
            const val ALTERNATIVE_URL = "https://youtu.be/"
            const val VIDEO_PARAM = "v"
        }
    }
}

