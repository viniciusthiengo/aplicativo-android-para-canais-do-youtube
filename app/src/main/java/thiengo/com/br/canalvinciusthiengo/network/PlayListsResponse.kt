package thiengo.com.br.canalvinciusthiengo.network

import android.content.Context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import thiengo.com.br.canalvinciusthiengo.data.dynamic.UtilDatabase
import thiengo.com.br.canalvinciusthiengo.model.PlayList
import thiengo.com.br.canalvinciusthiengo.model.parse.playlist.PlayListsParse

/**
 * Trabalha a resposta do servidor do YouTube à requisição
 * de dados de PlayLists liberadas no canal.
 *
 * @property context contexto do aplicativo.
 * @property callbackSuccess callback que deve ser
 * executado em caso de resposta bem sucedida.
 * @property callbackFailure callback que deve ser
 * executado em caso de resposta falha.
 * @constructor cria um objeto completo do tipo
 * [PlayListsResponse].
 */
class PlayListsResponse(
        private val context: Context,
        private val callbackSuccess: (List<PlayList>)->Unit = {},
        private val callbackFailure: (NetworkRetrieveDataProblem)->Unit = {}
    ) : Callback<PlayListsParse> {

    override fun onResponse(
        call: Call<PlayListsParse>,
        response: Response<PlayListsParse> ){
        parse( response = response )
    }

    override fun onFailure(
        call: Call<PlayListsParse>,
        t: Throwable ){

        callbackFailure( NetworkRetrieveDataProblem.NO_INTERNET_CONNECTION )
    }

    /**
     * Cria uma nova lista de PlayLists em app (incluindo no
     * banco de dados local) caso a resposta do YouTube à
     * requisição de dados de PlayLists disponíveis seja bem
     * sucedida.
     *
     * @param response resposta do backend YouTube já com o
     * parse Gson aplicado.
     */
    fun parse( response: Response<PlayListsParse> ){

        if( response.isSuccessful ){
            val playListParse = response.body()!!

            if( playListParse.items.isNotEmpty() ){
                val playLists = mutableListOf<PlayList>()

                playListParse.items.map{
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