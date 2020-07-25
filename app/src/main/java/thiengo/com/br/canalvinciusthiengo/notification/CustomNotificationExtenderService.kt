package thiengo.com.br.canalvinciusthiengo.notification

import android.net.UrlQuerySanitizer
import android.util.Log
import com.onesignal.NotificationExtenderService
import com.onesignal.OSNotificationReceivedResult
import org.json.JSONObject
import thiengo.com.br.canalvinciusthiengo.MainActivity
import thiengo.com.br.canalvinciusthiengo.config.OneSignalConfig
import thiengo.com.br.canalvinciusthiengo.config.YouTubeConfig
import thiengo.com.br.canalvinciusthiengo.data.UtilDatabase
import thiengo.com.br.canalvinciusthiengo.domain.LastVideo
import java.net.URI

class CustomNotificationExtenderService: NotificationExtenderService() {

    override fun onNotificationProcessing(
        notification: OSNotificationReceivedResult? ): Boolean {

        Log.i(
            MainActivity.LOG_TAG,
            "notification.payload.additionalData: ${notification?.payload?.additionalData}"
        )

        val lastVideo = getLastVideoFromJson(
            json = notification?.payload?.additionalData
        )

        if( lastVideo != null ){
            ifNewVideoSaveAndNotify( lastVideo = lastVideo )
        }
        return true
    }

    private fun getLastVideoFromJson( json: JSONObject? ) : LastVideo?{

        Log.i(
            MainActivity.LOG_TAG,
            "getLastVideoFromJson(): 1"
        )

        if( json == null
            || json.isNull( OneSignalConfig.Notification.VIDEO )
            || json.isNull( OneSignalConfig.Notification.TITLE ) ){
            return null
        }

        Log.i(
            MainActivity.LOG_TAG,
            "getLastVideoFromJson(): 2"
        )

        val url = json.getString( OneSignalConfig.Notification.VIDEO )
        val title = json.getString( OneSignalConfig.Notification.TITLE )
        var lastVideo : LastVideo? = null

        if( !url.isEmpty() && !title.isEmpty() ){

            Log.i(
                MainActivity.LOG_TAG,
                "getLastVideoFromJson(): 3 $url"
            )

            val urlQuery = UrlQuerySanitizer( url )

            if( !urlQuery.getValue( YouTubeConfig.Notification.VIDEO_PARAM ).isNullOrEmpty() ){
                lastVideo = LastVideo(
                    uid = urlQuery.getValue( YouTubeConfig.Notification.VIDEO_PARAM ),
                    title = title,
                    description = getDescriptionFromJson( json )
                )
                .apply {
                    thumbUrl = correctThumbUr()
                }
            }
            else if( url.contains( YouTubeConfig.Notification.ALTERNATIVE_URL ) ){
                val uri = URI( url )
                val path: String = uri.getPath()
                val uid = path.substring(path.lastIndexOf('/') + 1)

                lastVideo = LastVideo(
                    uid = uid,
                    title = title,
                    description = getDescriptionFromJson( json )
                )
                .apply {
                    thumbUrl = correctThumbUr()
                }
            }
        }

        return lastVideo
    }

    private fun getDescriptionFromJson( json: JSONObject )
        = if( !json.isNull( OneSignalConfig.Notification.DESCRIPTION ) ){
            json.getString( OneSignalConfig.Notification.DESCRIPTION )
        }
        else {
            OneSignalConfig.Notification.EMPTY
        }

    private fun ifNewVideoSaveAndNotify( lastVideo : LastVideo ){

        Log.i(
            MainActivity.LOG_TAG,
            "ifNewVideoSaveAndNotify: 1"
        )
        UtilDatabase
            .getInstance( context = this )
            .getLastVideo{

                Log.i(
                    MainActivity.LOG_TAG,
                    "ifNewVideoSaveAndNotify: 2"
                )

                if( it == null
                    || !it.uid.equals( lastVideo.uid ) ){

                    Log.i(
                        MainActivity.LOG_TAG,
                        "ifNewVideoSaveAndNotify: 3 ${MainActivity.APP_FOREGROUND}"
                    )

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