package thiengo.com.br.canalvinciusthiengo.notification

import android.util.Log
import com.onesignal.OSNotification
import com.onesignal.OneSignal
import thiengo.com.br.canalvinciusthiengo.MainActivity


class OneSignalNotificationReceivedHandler: OneSignal.NotificationReceivedHandler {

    override fun notificationReceived( notification: OSNotification? ) {

        Log.i(
            MainActivity.LOG_TAG,
            "notificationReceived(): NOTIFICAÇÃO ENTREGUE NO APARELHO"
        )

        val data = notification!!.payload.additionalData
        val customKey: String?

        if (data != null) {
            customKey = data.optString("customkey", null)
            if (customKey != null) Log.i(
                "OneSignalExample",
                "customkey set with value: $customKey"
            )
        }

        Log.i(
            MainActivity.LOG_TAG,
            "notificationReceived() -> data: $data"
        )
    }

}