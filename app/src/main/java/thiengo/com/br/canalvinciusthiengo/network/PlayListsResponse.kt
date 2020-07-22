package thiengo.com.br.canalvinciusthiengo.network

import android.content.Context
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import thiengo.com.br.canalvinciusthiengo.MainActivity
import thiengo.com.br.canalvinciusthiengo.data.UtilDatabase
import thiengo.com.br.canalvinciusthiengo.domain.PlayList
import thiengo.com.br.canalvinciusthiengo.network.playlist.PlayLists

class PlayListsResponse(
    private val context: Context,
    private val callbackSuccess: (List<PlayList>)->Unit = {},
    private val callbackFailure: (NetworkRetrieveDataProblem)->Unit = {} ) : Callback<PlayLists> {

    override fun onResponse(
        call: Call<PlayLists>,
        response: Response<PlayLists> ){

        parse( response = response )
    }

    override fun onFailure(
        call: Call<PlayLists>,
        t: Throwable ){

        Log.i(MainActivity.LOG_TAG, "t: ${t.message}")
        callbackFailure( NetworkRetrieveDataProblem.NO_INTERNET_CONNECTION )
    }

    fun parse( response: Response<PlayLists> ){

        if( response.isSuccessful ){
            val playListObj = response.body()!!

            if( playListObj.items.isNotEmpty() ){
                val playLists = mutableListOf<PlayList>()

                playListObj.items.map{
                    playLists.add(
                        PlayList(
                            uid = it.id,
                            title = it.title
                        )
                    )
                }

                UtilDatabase
                    .getInstance( context = context )
                    .savePlayLists( playLists = playLists )

                callbackSuccess( playLists )
            }
            else{
                Log.i(MainActivity.LOG_TAG, "t: NetworkRetrieveDataProblem.NO_PLAYLISTS")
                callbackFailure( NetworkRetrieveDataProblem.NO_PLAYLISTS )
            }
        }
        else{
            Log.i(MainActivity.LOG_TAG, "t: NetworkRetrieveDataProblem.NO_INTERNET_CONNECTION")
            callbackFailure( NetworkRetrieveDataProblem.NO_INTERNET_CONNECTION )
        }
    }
}