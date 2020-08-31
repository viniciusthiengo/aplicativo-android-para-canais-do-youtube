package thiengo.com.br.canalvinciusthiengo.network

import android.content.Context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import thiengo.com.br.canalvinciusthiengo.data.dynamic.UtilDatabase
import thiengo.com.br.canalvinciusthiengo.model.LastVideo
import thiengo.com.br.canalvinciusthiengo.model.parse.video.VideoParse

/**
 * Trabalha a resposta do servidor do YouTube à requisição
 * de dados de um novo "último vídeo" liberado em canal.
 *
 * @property context contexto do aplicativo.
 * @property callbackSuccess callback que deve ser
 * executado em caso de resposta bem sucedida.
 * @property callbackFailure callback que deve ser
 * executado em caso de resposta falha.
 * @constructor cria um objeto completo do tipo
 * [LastVideoResponse].
 */
class LastVideoResponse(
        private val context: Context,
        private val callbackSuccess: (LastVideo)->Unit = {},
        private val callbackFailure: (NetworkRetrieveDataProblem)->Unit = {}
    ) : Callback<VideoParse> {

    override fun onResponse(
        call: Call<VideoParse>,
        response: Response<VideoParse> ){
        parse( response = response )
    }

    override fun onFailure(
        call: Call<VideoParse>,
        t: Throwable ){
        callbackFailure( NetworkRetrieveDataProblem.NO_INTERNET_CONNECTION )
    }

    /**
     * Cria um novo [LastVideo] em app (incluindo no banco de
     * dados local) caso a resposta do YouTube à requisição
     * de dados do "último vídeo" seja bem sucedida.
     *
     * @param response resposta do backend YouTube já com o
     * parse Gson aplicado.
     */
    fun parse( response: Response<VideoParse> ){

        if( response.isSuccessful ){
            val video = response.body()!!

            if( video.id.isNotEmpty() ){
                val lastVideo = LastVideo(
                    uid = video.id,
                    title = video.title,
                    description = video.description
                ).apply {
                    thumbUrl = video.thumbUrl
                }

                UtilDatabase
                    .getInstance( context = context )
                    .saveLastVideo( lastVideo = lastVideo )

                callbackSuccess( lastVideo )
            }
            else{
                callbackFailure( NetworkRetrieveDataProblem.NO_VIDEO )
            }
        }
        else{
            callbackFailure( NetworkRetrieveDataProblem.NO_INTERNET_CONNECTION )
        }
    }
}