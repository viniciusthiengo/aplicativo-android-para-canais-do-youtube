package thiengo.com.br.canalvinciusthiengo.network

import android.content.Context
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import thiengo.com.br.canalvinciusthiengo.MainActivity
import thiengo.com.br.canalvinciusthiengo.data.UtilDatabase
import thiengo.com.br.canalvinciusthiengo.domain.PlayList
import thiengo.com.br.canalvinciusthiengo.network.playlistparse.PlayListsParse

class PlayListsResponse(
    private val context: Context,
    private val callbackSuccess: (List<PlayList>)->Unit = {},
    private val callbackFailure: (NetworkRetrieveDataProblem)->Unit = {} ) : Callback<PlayListsParse> {

    override fun onResponse(
        call: Call<PlayListsParse>,
        response: Response<PlayListsParse> ){

        parse( response = response )
    }

    override fun onFailure(
        call: Call<PlayListsParse>,
        t: Throwable ){

        Log.i(MainActivity.LOG_TAG, "t: ${t.message}")
        callbackFailure( NetworkRetrieveDataProblem.NO_INTERNET_CONNECTION )
    }

    fun parse( response: Response<PlayListsParse> ){

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
                callbackFailure( NetworkRetrieveDataProblem.NO_PLAYLISTS )
            }
        }
        else{
            callbackFailure( NetworkRetrieveDataProblem.NO_INTERNET_CONNECTION )
        }
    }
}