package thiengo.com.br.canalvinciusthiengo.config

// Last Vídeo
// https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=UCG3gFuIkRF3PpNkRk3Wp6dw&maxResults=1&order=date&key=AIzaSyA3PIJs6i88pdEjmVldLnsR7FeflPv6MJs

// PlayLIsts
// https://www.googleapis.com/youtube/v3/playlists?part=snippet&maxResults=200&channelId=UCG3gFuIkRF3PpNkRk3Wp6dw&order=date&key=AIzaSyA3PIJs6i88pdEjmVldLnsR7FeflPv6MJs


enum class YouTubeConfig private constructor( val value: String ){
    GOOGLE_DEV_KEY( value = "AIzaSyAScNRotW2GNg-lTT2lcBSV6GjxnzQOpn0" ), // Thiengo channel (BirdingBox)
    //GOOGLE_DEV_KEY( value = "AIzaSyA3PIJs6i88pdEjmVldLnsR7FeflPv6MJs" ), // Thiengo channel (Cursos)

    CHANNEL_ID( value = "UCG3gFuIkRF3PpNkRk3Wp6dw" ), // Thiengo channel
    //CHANNEL_ID( value = "UCglvzYlkZgttz63tUiaqooA" ), // Somebody else channel

    API_BASE_URL( value = "https://www.googleapis.com/" ),
    API_PATH_URL( value = "youtube/v3/search" ),
    API_SNIPPET_PARAMETER( value = "snippet" ),
    API_MAX_RESULTS_VIDEO_PARAMETER( value = "1" ),
    API_MAX_RESULTS_PLAYLISTS_PARAMETER( value = "500" ),
    API_ORDER_PARAMETER( value = "date" ),

    URL_VIDEO_PARAMETER( value = "v" ),
    ALTERNATIVE_URL( value = "https://youtu.be/" )
}