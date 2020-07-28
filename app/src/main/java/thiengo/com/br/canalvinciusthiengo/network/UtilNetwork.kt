package thiengo.com.br.canalvinciusthiengo.network

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import thiengo.com.br.canalvinciusthiengo.config.YouTubeConfig
import thiengo.com.br.canalvinciusthiengo.model.LastVideo
import thiengo.com.br.canalvinciusthiengo.model.PlayList

/**
 * Classe utilitária que permite fácil acesso à
 * API de comunicação remota do aplicativo.
 *
 * Assim é possível obter de maneira imediata e
 * não verbosa uma nova comunicação remota.
 *
 * @property context contexto do aplicativo.
 * @constructor cria um objeto completo do tipo
 * UtilNetwork.
 */
class UtilNetwork private constructor(
    private val context: Context ){

    companion object{
        /**
         * Propriedade responsável por conter a única
         * instância de UtilNetwork disponível
         * durante toda a execução do aplicativo.
         */
        private var instance: UtilNetwork? = null

        /**
         * Método que aplica, junto à propriedade
         * instance, o padrão Singleton em classe.
         * Grantindo que somente uma instância de
         * UtilNetwork estará disponível durante
         * toda a execução do app. Ajudando a
         * diminuir a possibilidade de vazamento
         * de memória.
         *
         * @param context contexto do aplicativo.
         * @return instância única de UtilNetwork.
         */
        fun getInstance( context: Context ) : UtilNetwork {
            if( instance == null ){
                instance = UtilNetwork( context = context )
            }
            return instance!!
        }
    }

    /**
     * Retorna um objeto de serviço completo para comunicação
     * remota com o servidor de dados do YouTube.
     *
     * @return serviço Retrofit para comunicação remota.
     */
    private fun getYouTubeService()
        = Retrofit.Builder()
            .baseUrl( YouTubeConfig.ApiV3.BASE_URL )
            .addConverterFactory( GsonConverterFactory.create() )
            .build()
            .create( YouTubeService::class.java )

    /**
     * Realiza a comunicação remota com o servidor de dados do
     * YouTube para obter os dados do "último vídeo" liberado
     * em canal.
     *
     * @param networkRequestMode modelo de conexão com o servidor
     * remoto.
     * @property callbackSuccess callback que deve ser executado
     * em caso de resposta bem sucedida.
     * @property callbackFailure callback que deve ser executado
     * em caso de resposta falha.
     */
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

    /**
     * Realiza a comunicação remota com o servidor de dados do
     * YouTube para obter os dados de PlayLists disponíveis
     * em canal.
     *
     * @param networkRequestMode modelo de conexão com o servidor
     * remoto.
     * @property callbackSuccess callback que deve ser executado
     * em caso de resposta bem sucedida.
     * @property callbackFailure callback que deve ser executado
     * em caso de resposta falha.
     */
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