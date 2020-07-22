package thiengo.com.br.canalvinciusthiengo.network

import android.content.Context
import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import thiengo.com.br.canalvinciusthiengo.MainActivity
import thiengo.com.br.canalvinciusthiengo.domain.LastVideo
import thiengo.com.br.canalvinciusthiengo.domain.PlayList

class UtilNetwork private constructor(
        private val context: Context
    ) {

    companion object{
        const val FIREBASE_CM_SERVER_KEY = "AAAAM6VZ2kE:APA91bGaR_gveYgDHZ2WL9U5amxlBMtOPVTBrUolgUIYkXffk9WeTL3JZGTEFn715RGTD3eXd8N1LEWaByAaRguc1Fz5kV2HN82oehd6A6jceh_JpgayOoqEY6cs0ipEX59h6dj3D1tg"
        const val FIREBASE_CM_SENDER_ID = "221817461313"
        const val ONE_SIGNAL_APP_ID = "e4c1751c-0ce9-40f7-8590-f9daa7927539"

        const val GOOGLE_DEV_KEY = "AIzaSyAScNRotW2GNg-lTT2lcBSV6GjxnzQOpn0" // Thiengo channel (BirdingBox)
        //const val GOOGLE_DEV_KEY = "AIzaSyA3PIJs6i88pdEjmVldLnsR7FeflPv6MJs" // Thiengo channel (Cursos)

        const val CHANNEL_ID = "UCG3gFuIkRF3PpNkRk3Wp6dw" // Thiengo channel
        //const val CHANNEL_ID = "UCglvzYlkZgttz63tUiaqooA" // Somebody else channel

        private const val BASE_URI = "https://www.googleapis.com/"
        private var instance: UtilNetwork? = null

        fun getInstance( context: Context) : UtilNetwork {
            if( instance == null ){
                instance = UtilNetwork( context = context )
            }
            return instance!!
        }
    }

    private fun getYouTubeService()
        = Retrofit.Builder()
            .baseUrl( BASE_URI )
            .addConverterFactory( GsonConverterFactory.create() )
            .build()
            .create( YouTubeService::class.java )

    fun retrieveLastVideo(
        networkRequestMode: NetworkRequestMode = NetworkRequestMode.ASYNCHRONOUS,
        callbackSuccess: (LastVideo)->Unit = {},
        callbackFailure: (NetworkRetrieveDataProblem)->Unit = {} ){

        val service = getYouTubeService()
        val call = service.lastVideo()
        val lastVideoResponse = LastVideoResponse(
            context = context,
            callbackSuccess = callbackSuccess,
            callbackFailure = callbackFailure
        )

        if( networkRequestMode == NetworkRequestMode.ASYNCHRONOUS ){
            call.enqueue( lastVideoResponse )
        }
        else{
            try{
                lastVideoResponse.parse(
                    response = call.execute()
                )
            }
            catch( e: Exception ){}
        }
    }

    fun retrievePlayLists(
        networkRequestMode: NetworkRequestMode = NetworkRequestMode.ASYNCHRONOUS,
        callbackSuccess: (List<PlayList>)->Unit = {},
        callbackFailure: (NetworkRetrieveDataProblem)->Unit = {} ){

        val service = getYouTubeService()
        val call = service.playLists()
        val playListsResponse = PlayListsResponse(
            context = context,
            callbackSuccess = callbackSuccess,
            callbackFailure = callbackFailure
        )

        if( networkRequestMode == NetworkRequestMode.ASYNCHRONOUS ){
            call.enqueue( playListsResponse )
        }
        else{
            try{
                playListsResponse.parse(
                    response = call.execute()
                )
            }
            catch( e: Exception ){}
        }
    }
}