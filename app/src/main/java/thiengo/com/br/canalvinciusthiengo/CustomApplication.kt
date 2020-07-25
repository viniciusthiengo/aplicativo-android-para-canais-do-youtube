package thiengo.com.br.canalvinciusthiengo

import android.app.Application
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.onesignal.OneSignal
import thiengo.com.br.canalvinciusthiengo.worker.CatchChannelDataWorker
import java.util.concurrent.TimeUnit

class CustomApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        oneSignalInit()
        backgroundWork()
    }

    private fun backgroundWork(){
        val request = PeriodicWorkRequestBuilder<CatchChannelDataWorker>(
            CatchChannelDataWorker.REPEAT_INTERVAL,
            TimeUnit.MINUTES
        ).build()

        /*
         * Configuração que garante que mesmo com uma "re-invocação"
         * de enfileramente de "work" não haverá work repetido em
         * lista de execução do WorkManager.
         * */
        WorkManager
            .getInstance( this )
            .enqueueUniquePeriodicWork(
                CatchChannelDataWorker.NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                request
            )
    }

    private fun oneSignalInit()
        = OneSignal.startInit( this )
            .inFocusDisplaying( OneSignal.OSInFocusDisplayOption.Notification )
            .unsubscribeWhenNotificationsAreDisabled( true )
            .init()
}