package thiengo.com.br.canalvinciusthiengo.domain

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import thiengo.com.br.canalvinciusthiengo.config.YouTubeConfig

@Entity
data class LastVideo(
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String = "" ) : Parcelable {

    @ColumnInfo(name = "thumb_url")
    var thumbUrl: String = ""
        set(value) {
            if (value.isNotEmpty()) {
                field = value
            }
            field = String.format(
                YouTubeConfig.Channel.VIDEO_THUMB_URL_TEMPLATE,
                uid
            )
        }

    fun appUri() = Uri.parse(
        String.format(
            YouTubeConfig.Channel.VIDEO_URL_TEMPLATE,
            uid
        )
    )

    fun correctThumbUr(): String {
        if (thumbUrl.isNotEmpty()) {
            return thumbUrl
        }
        return String.format(
            YouTubeConfig.Channel.VIDEO_THUMB_URL_TEMPLATE,
            uid
        )
    }

    /* Parcelable boilerplate code. */
    constructor( source: Parcel ) : this (
        source.readString()!!,
        source.readString()!!,
        source.readString()!!
    )

    override fun describeContents() = 0

    override fun writeToParcel(
        dest: Parcel,
        flags: Int )
        = with( dest ){
            writeString( uid )
            writeString( title )
            writeString( description )
        }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<LastVideo> = object : Parcelable.Creator<LastVideo> {

            override fun createFromParcel( source: Parcel ) : LastVideo
                = LastVideo( source )

            override fun newArray( size: Int ) : Array<LastVideo?>
                = arrayOfNulls( size )
        }
    }
}