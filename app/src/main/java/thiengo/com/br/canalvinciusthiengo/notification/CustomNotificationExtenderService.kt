package thiengo.com.br.canalvinciusthiengo.notification

import android.net.UrlQuerySanitizer
import com.onesignal.NotificationExtenderService
import com.onesignal.OSNotificationReceivedResult
import org.json.JSONObject
import thiengo.com.br.canalvinciusthiengo.config.OneSignalConfig
import thiengo.com.br.canalvinciusthiengo.config.YouTubeConfig
import thiengo.com.br.canalvinciusthiengo.data.dynamic.UtilDatabase
import thiengo.com.br.canalvinciusthiengo.model.LastVideo
import java.net.URI

/**
 * Serviço responsável por interceptar a notificação
 * OneSignal enviada ao aparelho e assim trabalhar
 * a configuração personalizada dessa notificação
 * push.
 *
 * Este serviço deve também estar configurado no
 * AndroidManifest.xml do app como a seguir:
 *
 *  <service
 *      android:name=".notification.CustomNotificationExtenderService"
 *      android:exported="false"
 *      android:permission="android.permission.BIND_JOB_SERVICE">
 *          <intent-filter>
 *              <action android:name="com.onesignal.NotificationExtender" />
 *          </intent-filter>
 *  </service>
 *
 * @constructor cria um objeto completo do tipo
 * CustomNotificationExtenderService.
 */
class CustomNotificationExtenderService: NotificationExtenderService() {

    /**
     * Processa a notificação OneSignal que foi
     * enviada ao aparelho.
     *
     * @param notification notificação OneSignal.
     * @return um Boolean que indica se o
     * comportamento comum de geração de notificação
     * da OneSignal API deve (false) ou não (true)
     * continuar.
     */
    override fun onNotificationProcessing(
        notification: OSNotificationReceivedResult? ): Boolean {

        val lastVideo = getLastVideoFromJson(
            json = notification?.payload?.additionalData
        )

        if( lastVideo != null ){
            ifNewLastVideoThenSaveAndNotify( lastVideo = lastVideo )
        }

        return true
    }

    /**
     * Gera um objeto LastVideo completo de acordo com
     * os dados JSON recebidos de notificação OneSignal.
     *
     * @param json dados JSON obtidos de notificação.
     * @return objeto LastVideo completo.
     */
    private fun getLastVideoFromJson(
        json: JSONObject? ) : LastVideo? {

        /*
         * Padrão Cláusula de guarda. Se as premissas não
         * estiverem presentes, então nem mesmo continue
         * com a execução.
         * */
        if( json == null
            || json.isNull( OneSignalConfig.Notification.VIDEO )
            || json.isNull( OneSignalConfig.Notification.TITLE ) ){
            return null
        }

        val url = json.getString( OneSignalConfig.Notification.VIDEO )
        val title = json.getString( OneSignalConfig.Notification.TITLE )
        var lastVideo : LastVideo? = null

        if( !url.isEmpty() && !title.isEmpty() ){

            val urlQuery = UrlQuerySanitizer( url )

            if( !urlQuery.getValue( YouTubeConfig.Notification.VIDEO_PARAM ).isNullOrEmpty() ){
                lastVideo = LastVideo(
                    uid = urlQuery.getValue( YouTubeConfig.Notification.VIDEO_PARAM ),
                    title = title,
                    description = getDescriptionFromJson( json )
                )
                .apply {
                    thumbUrl = YouTubeConfig.Notification.EMPTY
                }
            }
            else if( url.contains( YouTubeConfig.Notification.ALTERNATIVE_URL ) ){
                val uri = URI( url )
                val path: String = uri.getPath()
                val uid = path
                    .substring(
                        path.lastIndexOf('/') + 1
                    )

                lastVideo = LastVideo(
                    uid = uid,
                    title = title,
                    description = getDescriptionFromJson( json )
                )
                .apply {
                    thumbUrl = YouTubeConfig.Notification.EMPTY
                }
            }
        }

        return lastVideo
    }

    /**
     * Retorna o dado de descrição do "último vídeo" se
     * ele estiver presente no JSON de dados que chegou
     * junto a notificação OneSignal.
     *
     * @param json dados JSON obtidos de notificação.
     * @return dado de descrição do vídeo.
     */
    private fun getDescriptionFromJson( json: JSONObject )
        = if( !json.isNull( OneSignalConfig.Notification.DESCRIPTION ) ){
            json.getString( OneSignalConfig.Notification.DESCRIPTION )
        }
        else {
            OneSignalConfig.Notification.EMPTY
        }

    /**
     * Salva em banco de dados e notifica o usuário
     * caso os dados que chegaram ao app sejam de um
     * novo "último vídeo" liberado no canal YouTube
     * do aplicativo.
     *
     * @param lastVideo último vídeo liberado em canal e
     * que chegou ao aplicativo.
     */
    private fun ifNewLastVideoThenSaveAndNotify(
        lastVideo : LastVideo ){

        UtilDatabase
            .getInstance( context = this )
            .getLastVideo{

                if( it == null
                    || !it.uid.equals( lastVideo.uid )
                    || !it.title.equals( lastVideo.title )
                    || !it.description.equals( lastVideo.description ) ){

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