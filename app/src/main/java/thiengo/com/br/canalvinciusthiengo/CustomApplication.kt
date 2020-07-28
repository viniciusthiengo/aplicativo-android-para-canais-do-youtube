package thiengo.com.br.canalvinciusthiengo

import android.app.Application
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.onesignal.OneSignal
import thiengo.com.br.canalvinciusthiengo.network.worker.CatchChannelDataWorker
import java.util.concurrent.TimeUnit

/**
 * Classe de sistema e de objeto único enquanto
 * o aplicativo estiver em execução.
 *
 * Com essa característica está é a classe
 * responsável por iniciar as entidades de
 * WorkManager (trabalho em background) e
 * OneSignal (notificação push). Entidades essas
 * que precisam ser invocadas logo no início da
 * execução do aplicativo ainda na primeira vez
 * que ele é acessado pelo usuário.
 *
 * Note que para essa classe ser invocada pelo
 * sistema ela precisa estar configurada no
 * AndroidManifest.xml como:
 *
 * <application
 *      android:name=".CustomApplication"
 *      ...>
 *
 * @constructor cria um objeto completo do tipo
 * CustomApplication.
 */
class CustomApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        oneSignalInit()
        backgroundWork()
    }

    /**
     * Inicializa o WorkManager para execuções
     * não precisas, mas intervaladas, em
     * background.
     */
    private fun backgroundWork(){
        val request = PeriodicWorkRequestBuilder<CatchChannelDataWorker>(
            CatchChannelDataWorker.REPEAT_INTERVAL,
            TimeUnit.MINUTES
        ).build()

        /*
         * Configuração de WorkManager que
         * garante que mesmo com uma
         * "re-invocação" de enfileramente de
         * "work" não haverá work repetido em
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

    /**
     * Inicializa o OneSignal e registra o
     * usuário do app para que ele já consiga
     * receber as notificações push do canal
     * do aplicativo.
     *
     * Com a configuração a seguir é preciso
     * que também tenha definido no aplicativo
     * um serviço do tipo
     * NotificationExtenderService para que as
     * notificações push sejam interceptadas
     * e trabalhadas de maneira personalizada.
     */
    private fun oneSignalInit(){
        OneSignal.startInit( this )
            .inFocusDisplaying( OneSignal.OSInFocusDisplayOption.Notification )
            .unsubscribeWhenNotificationsAreDisabled( true )
            .init()
    }
}