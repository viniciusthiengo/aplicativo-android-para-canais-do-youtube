package thiengo.com.br.canalvinciusthiengo.network.worker

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import thiengo.com.br.canalvinciusthiengo.data.dynamic.UtilDatabase
import thiengo.com.br.canalvinciusthiengo.model.LastVideo
import thiengo.com.br.canalvinciusthiengo.network.NetworkRequestMode
import thiengo.com.br.canalvinciusthiengo.network.UtilNetwork
import thiengo.com.br.canalvinciusthiengo.notification.UtilNotification

/**
 * Classe [Worker] de trabalho periódico em background,
 * mesmo quando o aplicativo não está em memória.
 *
 * Com o objeto desta classe [Worker] é possível manter
 * os dados de "último vídeo" e de PlayLists do canal
 * atualizados em app.
 *
 * @property context contexto do aplicativo.
 * @property params parâmetros de trabalho do
 * WorkManager.
 * @constructor cria um objeto completo do tipo
 * [CatchChannelDataWorker].
 */
class CatchChannelDataWorker(
    private val context: Context,
    params: WorkerParameters ) : Worker( context, params ) {

    companion object{
        /**
         * Constantes com alguns dados da configuração
         * inicial de WorkManager.
         */
        const val NAME = "sync_local_database"
        const val REPEAT_INTERVAL : Long = 18
    }

    override fun doWork(): Result {
        UtilNetwork
            .getInstance( context = context )
            .retrievePlayLists(
                networkRequestMode = NetworkRequestMode.SYNCHRONOUS,
                callbackSuccess = {
                    retrieveLocalLastVideo()
                }
            )

        return Result.success()
    }

    /**
     * Obtém, por meio de callback, o "último vídeo" já
     * salvo em banco de dados local.
     */
    private fun retrieveLocalLastVideo(){
        UtilDatabase
            .getInstance( context = context )
            .getLastVideo{
                retrieveServerLastVideo( oldLastVideo = it )
            }
    }

    /**
     * Obtém, do YouTube Data API (servidor remoto) e por
     * meio de callback, o "último vídeo" disponível em
     * canal YouTube vinculado ao app.
     *
     * @param oldLastVideo "último vídeo", obtido do banco
     * de dados local.
     */
    private fun retrieveServerLastVideo(
        oldLastVideo: LastVideo? ){

        UtilNetwork
            .getInstance( context = context )
            .retrieveLastVideo(
                networkRequestMode = NetworkRequestMode.SYNCHRONOUS,
                callbackSuccess = {

                    /**
                     * Somente cria uma nova notificação se o
                     * último vídeo liberado no canal não está
                     * ainda salvo no banco de dados local.
                     */
                    if( oldLastVideo == null
                        || !oldLastVideo.uid.equals( it.uid )
                        || !oldLastVideo.title.equals( it.title )
                        || !oldLastVideo.title.equals( it.description )){

                        UtilNotification
                            .getInstance( context = context )
                            .createBigPictureNotification( lastVideo = it )
                    }
                }
            )
    }
}