package thiengo.com.br.canalvinciusthiengo.data.dynamic

import android.content.Context
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import thiengo.com.br.canalvinciusthiengo.model.LastVideo
import thiengo.com.br.canalvinciusthiengo.model.PlayList
import kotlin.concurrent.thread

/**
 * Classe utilitária que permite fácil acesso à
 * base de dados local, Room API.
 *
 * Assim é possível obter de maneira imediata e
 * não verbosa os métodos de inserção e obtenção
 * de dados de: último vídeo; e PlayLists do canal.
 *
 * @property context contexto do aplicativo.
 * @constructor cria um objeto completo do tipo
 * UtilDatabase.
 */
class UtilDatabase private constructor(
    private val context: Context ){

    companion object{
        /**
         * Propriedade responsável por conter a
         * única instância de UtilDatabase
         * disponível durante toda a execução do
         * aplicativo.
         */
        private var instance: UtilDatabase? = null

        /**
         * Método que aplica, junto à propriedade
         * instance, o padrão Singleton em classe.
         * Grantindo que somente uma instância de
         * UtilDatabase estará disponível durante
         * toda a execução do app. Ajudando a
         * diminuir a possibilidade de vazamento
         * de memória.
         *
         * @param context contexto do aplicativo.
         * @return instância única de UtilDatabase.
         */
        fun getInstance( context: Context ) : UtilDatabase {
            if( instance == null ){
                instance = UtilDatabase(
                    context = context
                )
            }
            return instance!!
        }
    }

    /**
     * Retorna uma instância do banco de dados
     * local para que seja possível manipular os
     * dados nele.
     *
     * @return conexão com o banco de dados local.
     */
    private fun getDatabase() : ChannelDatabase
        = ChannelDatabase.getInstance( context = context )

    /**
     * Salva em banco de dados local os dados do
     * último vídeo do canal obtido pelos
     * algoritmos do app.
     *
     * Note que qualquer acesso ao banco de dados
     * local via Room API deve ser fora da Thread
     * Principal, por isso a necessidade de
     * thread{} no código do método a seguir.
     *
     * @param lastVideo último vídeo disponível
     * no canal.
     */
    fun saveLastVideo( lastVideo: LastVideo ){
        thread{
            try {
                val dataBase = getDatabase()

                /**
                 * Garantindo que sempre terá
                 * somente um vídeo na tabela
                 * de vídeos.
                 */
                dataBase
                    .lastVideoDao()
                    .delete()

                dataBase
                    .lastVideoDao()
                    .insert(
                        lastVideo = lastVideo
                    )

                dataBase.close()

                newLastVideoBroadcast( lastVideo = lastVideo )
            }
            catch( e :Exception ){}
        }
    }

    /**
     * Via LocalBroadcastManager informa ao
     * fragmento LastVideoFragment os dados do
     * novo "último vídeo" que deve aparecer em
     * tela.
     *
     * Somente terá efeito se o aplicativo
     * estiver em foreground (primeiro plano).
     *
     * @param lastVideo último vídeo disponível
     * no canal.
     */
    private fun newLastVideoBroadcast(
        lastVideo: LastVideo ){

        val intent = Intent( NewLastVideoBroadcast.FILTER_KEY )

        intent.putExtra(
            NewLastVideoBroadcast.DATA_KEY,
            lastVideo
        )

        LocalBroadcastManager
            .getInstance( context )
            .sendBroadcast( intent )
    }

    /**
     * Retorna, via callback, o último vídeo do
     * canal salvo em persistência local ou null
     * caso não haja algum.
     *
     * Note que qualquer acesso ao banco de dados
     * local via Room API deve ser fora da Thread
     * Principal, por isso a necessidade de
     * thread{} no código do método a seguir.
     *
     * @param callback função que vai trabalhar o
     * retorno de dataBase.lastVideoDao().get().
     */
    fun getLastVideo(
        callback: (LastVideo?)->Unit ){

        thread {
            try {
                val dataBase = getDatabase()
                val lastVideo = dataBase.lastVideoDao().get()
                dataBase.close()

                callback( lastVideo )
            }
            catch( e :Exception ){}
        }
    }

    /**
     * Salva em banco de dados local os dados das
     * PlayLists do canal obtidas pelos algoritmos
     * do app.
     *
     * Note que qualquer acesso ao banco de dados
     * local via Room API deve ser fora da Thread
     * Principal, por isso a necessidade de
     * thread{} no código do método a seguir.
     *
     * @param playLists PlayLists disponíveis no
     * canal.
     */
    fun savePlayLists(
        playLists: List<PlayList> ){

        thread{
            try {
                val dataBase = getDatabase()

                /**
                 * Garantindo que sempre terá em
                 * banco de dados local somente
                 * as PlayLists ainda ativas no
                 * canal YouTube do aplicativo.
                 */
                dataBase
                    .playListDao()
                    .deleteAll()

                dataBase
                    .playListDao()
                    .insertAll(
                        playLists = playLists
                    )

                dataBase.close()
            }
            catch( e :Exception ){}
        }
    }

    /**
     * Retorna, via callback, todas as PlayLists
     * do canal salvas em persistência local ou
     * null caso não haja alguma.
     *
     * Note que qualquer acesso ao banco de dados
     * local via Room API deve ser fora da Thread
     * Principal, por isso a necessidade de
     * thread{} no código do método a seguir.
     *
     * @param callback função que vai trabalhar o
     * retorno de dataBase.playListDao().getAll().
     */
    fun getAllPlayLists(
        callback: (List<PlayList>? )->Unit ){

        thread {
            try {
                val dataBase = getDatabase()
                val playLists = dataBase.playListDao().getAll()
                dataBase.close()

                callback( playLists )
            }
            catch( e :Exception ){}
        }
    }
}