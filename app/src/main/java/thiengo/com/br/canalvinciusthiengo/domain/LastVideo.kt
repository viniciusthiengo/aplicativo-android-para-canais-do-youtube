package thiengo.com.br.canalvinciusthiengo.domain

import android.net.Uri
import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import thiengo.com.br.canalvinciusthiengo.MainActivity

@Entity
data class LastVideo(
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String = "" ){

    companion object {
        private const val VIDEO_URI = "https://www.youtube.com/watch?v="
        private const val THUMB_URL_BY_UID_TEMPLATE = "https://i.ytimg.com/vi/%s/hqdefault.jpg"
    }

    /*@PrimaryKey val uid: String
    @ColumnInfo(name = "title") val title: String
    @ColumnInfo(name = "description") val description: String = ""
    //@ColumnInfo(name = "thumb_url") val thumbUrl: String = ""*/

    /*@ColumnInfo(name = "thumb_url")
    public val thumbUrl: String = ""
        get(){
            Log.i(
                MainActivity.LOG_TAG,
                "THUMB URL: ${field}"
            )

            if( field.isNotEmpty() ){
                return field
            }
            return thumbUrlByUid()
        }*/

    @ColumnInfo(name = "thumb_url")
    public var thumbUrl: String = ""
        set( value ){
            Log.i(
                MainActivity.LOG_TAG,
                "THUMB URL: ${value}"
            )

            if( value.isNotEmpty() ){
                field = value
            }
            field = String.format( THUMB_URL_BY_UID_TEMPLATE, uid )
        }

    fun appUri()
        = Uri.parse(
            String.format(
                "%s%s",
                VIDEO_URI,
                uid
            )
        )

    fun correctThumbUr() : String {
        if( thumbUrl.isNotEmpty() ){
            return thumbUrl
        }
        return String.format( THUMB_URL_BY_UID_TEMPLATE, uid )
    }

}