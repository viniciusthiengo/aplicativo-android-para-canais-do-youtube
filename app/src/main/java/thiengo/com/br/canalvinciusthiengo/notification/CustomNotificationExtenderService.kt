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
        if( json == null
            || json.isNull( OneSignalConfig.NOTIFICATION_VIDEO_PARAMETER.value )
            || json.isNull( OneSignalConfig.NOTIFICATION_TITLE_PARAMETER.value ) ){
            return null
        }

        val url = json.getString( OneSignalConfig.NOTIFICATION_VIDEO_PARAMETER.value )
        val title = json.getString( OneSignalConfig.NOTIFICATION_TITLE_PARAMETER.value )
        var lastVideo : LastVideo? = null

        if( !url.isEmpty() && !title.isEmpty() ){
            val urlQuery = UrlQuerySanitizer( url )

            if( !urlQuery.getValue( YouTubeConfig.URL_VIDEO_PARAMETER.value ).isNullOrEmpty() ){
                lastVideo = LastVideo(
                    uid = urlQuery.getValue( YouTubeConfig.URL_VIDEO_PARAMETER.value ),
                    title = title,
                    description = getDescriptionFromJson( json )
                )
                .apply {
                    thumbUrl = correctThumbUr()
                }
            }
            else if( url.contains( YouTubeConfig.ALTERNATIVE_URL.value ) ){
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
        = if( !json.isNull( OneSignalConfig.NOTIFICATION_DESCRIPTION_PARAMETER.value ) ){
            json.getString( OneSignalConfig.NOTIFICATION_DESCRIPTION_PARAMETER.value )
        }
        else {
            OneSignalConfig.NOTIFICATION_EMPTY_PARAMETER.value
        }

    private fun ifNewVideoSaveAndNotify( lastVideo : LastVideo ){
        UtilDatabase
            .getInstance( context = this )
            .getLastVideo{

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