package thiengo.com.br.canalvinciusthiengo.worker

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import thiengo.com.br.canalvinciusthiengo.MainActivity
import thiengo.com.br.canalvinciusthiengo.data.UtilDatabase
import thiengo.com.br.canalvinciusthiengo.domain.LastVideo
import thiengo.com.br.canalvinciusthiengo.network.NetworkRequestMode
import thiengo.com.br.canalvinciusthiengo.network.UtilNetwork
import thiengo.com.br.canalvinciusthiengo.notification.UtilNotification

class CatchChannelDataWorker(
    val context: Context,
    val params: WorkerParameters ) : Worker( context, params ) {

    companion object{
        const val NAME = "sync_local_database"
        const val REPEAT_INTERVAL : Long = 20
    }

    override fun doWork(): Result {
        Log.i(MainActivity.LOG_TAG, "INSIDE 1")

        UtilNetwork
            .getInstance( context = context )
            .retrievePlayLists(
                networkRequestMode = NetworkRequestMode.SYNCHRONOUS,
                callbackSuccess = {

                    UtilDatabase
                        .getInstance( context = context )
                        .getLastVideo{
                            retrieveLastVideo( oldLastVideo = it )
                        }
                }
            )

        return Result.success()
    }

    private fun retrieveLastVideo( oldLastVideo: LastVideo? ){
        Log.i(MainActivity.LOG_TAG, "INSIDE 2")

        UtilNetwork
            .getInstance( context = context )
            .retrieveLastVideo(
                networkRequestMode = NetworkRequestMode.SYNCHRONOUS,
                callbackSuccess = {

                    /*
                     * Somente cria uma nova notificação se o
                     * último vídeo liberado no canal não está
                     * ainda salvo no banco de dados local do
                     * aplicativo.
                     * */
                    if( oldLastVideo == null
                        || !oldLastVideo.uid.equals( it.uid ) ){

                        Log.i(MainActivity.LOG_TAG, "INSIDE 3")

                        UtilNotification
                            .getInstance( context = context )
                            .createBigPictureNotification( lastVideo = it )
                    }
                }
            )
    }
}