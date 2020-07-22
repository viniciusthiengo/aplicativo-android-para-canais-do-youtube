package thiengo.com.br.canalvinciusthiengo.notification

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.onesignal.NotificationExtenderService
import com.onesignal.OSNotificationReceivedResult
import org.json.JSONObject
import thiengo.com.br.canalvinciusthiengo.MainActivity
import thiengo.com.br.canalvinciusthiengo.data.UtilDatabase
import thiengo.com.br.canalvinciusthiengo.domain.LastVideo

class CustomNotificationExtenderService: NotificationExtenderService() {

    override fun onNotificationProcessing(
        notification: OSNotificationReceivedResult? ): Boolean {

        Log.i(
            MainActivity.LOG_TAG,
            "notification.payload.additionalData: ${notification?.payload?.additionalData}"
        )
        val data = notification?.payload?.additionalData

        if( data != null ){

            val jsonObject = JsonParser().parse( data.toString() ).asJsonObject

            Log.i(
                MainActivity.LOG_TAG,
                "notification.payload.additionalData: ${jsonObject}"
            )

            val gson = Gson()
            var aux : LastVideo? = null
            val lastVideo = gson.fromJson( jsonObject, LastVideo::class.java )

            if( lastVideo != null
                && lastVideo.uid.isNotEmpty()
                && lastVideo.title.isNotEmpty() ){

                UtilDatabase
                    .getInstance( context = this )
                    .getLastVideo{
                        Log.i(MainActivity.LOG_TAG, "UtilNotification - INSIDE Service")

                        Log.i( MainActivity.LOG_TAG, "it: $it" )
                        Log.i( MainActivity.LOG_TAG, "lastVideo: $lastVideo" )

                        if( it == null
                            || !it.uid.equals( lastVideo.uid ) ){

                            UtilDatabase
                                .getInstance( context = this )
                                .saveLastVideo( lastVideo = lastVideo )

                            UtilNotification
                                .getInstance( context = this )
                                .createBigPictureNotification( lastVideo = lastVideo )
                        }
                    }
            }
        }

        return true
    }
}