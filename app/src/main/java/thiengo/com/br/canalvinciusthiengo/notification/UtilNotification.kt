package thiengo.com.br.canalvinciusthiengo.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.squareup.picasso.Picasso
import thiengo.com.br.canalvinciusthiengo.MainActivity
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.domain.LastVideo

class UtilNotification private constructor(
        private val context: Context
    ){

    companion object{
        const val CHANNEL_ID = "new_channel_video"

        /*
         * Padrão Singleton.
         * */
        private var instance: UtilNotification? = null

        fun getInstance( context: Context ) : UtilNotification {
            if( instance == null ){
                instance = UtilNotification( context = context )
            }
            return instance!!
        }
    }

    fun createBigPictureNotification( lastVideo: LastVideo ){
        val bitmapBigPicture = Picasso
            .get()
            .load( lastVideo.correctThumbUr() )
            .get()

        createNotification(
            lastVideo = lastVideo,
            bitmapBigPicture = bitmapBigPicture
        )
    }

    /**
     * Create a Notification that is shown as a heads-up notification if possible.
     *
     * For this codelab, this is used to show a notification so that you know when different steps
     * of the background work chain are starting
     *
     * @param message Message shown on the notification
     * @param context Context needed to create Toast
     */
    fun createNotification(
            lastVideo: LastVideo,
            bitmapBigPicture: Bitmap? = null
        ){

        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ) {
            createNotificationChannel()
        }

        val notificationBuilder = getNotification(
            lastVideo = lastVideo,
            bitmapBigPicture = bitmapBigPicture
        )

        NotificationManagerCompat
            .from( context )
            .notify(
                1,
                notificationBuilder
            )
    }

    @RequiresApi( Build.VERSION_CODES.O )
    private fun createNotificationChannel(){
        val name = context.getString(
            R.string.notification_verbose_name
        )
        val description = context.getString(
            R.string.notification_verbose_description
        )
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(
            CHANNEL_ID,
            name,
            importance
        )
        .apply{
            this.description = description
        }

        // Add the channel
        val notificationManager = context.getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager?

        notificationManager?.createNotificationChannel( channel )
    }

    private fun getNotification(
        lastVideo: LastVideo,
        bitmapBigPicture: Bitmap? = null ) : Notification {

        val notification = NotificationCompat
            .Builder(
                context,
                CHANNEL_ID
            )
            .setSmallIcon( R.drawable.ic_circular_play )
            .setContentTitle(
                context.getString( R.string.notification_verbose_name )
            )
            .setContentText( lastVideo.title )
            .setPriority( NotificationCompat.PRIORITY_HIGH )
            .setCategory( NotificationCompat.CATEGORY_RECOMMENDATION )
            .setContentIntent( getPendingIntent() )
            .setVisibility( NotificationCompat.VISIBILITY_PUBLIC )
            .setAutoCancel( true )

        if( bitmapBigPicture != null ){
            notification
                .setLargeIcon( bitmapBigPicture )
                .setStyle(
                    NotificationCompat
                        .BigPictureStyle()
                        .bigPicture( bitmapBigPicture )
                        .bigLargeIcon( null )
                )
        }

        return notification.build()
    }

    private fun getPendingIntent() : PendingIntent {
        val intent = Intent(
            context,
            MainActivity::class.java
        ).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        return pendingIntent
    }
}