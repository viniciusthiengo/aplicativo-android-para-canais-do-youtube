package thiengo.com.br.canalvinciusthiengo.domain

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class LastVideo(
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "thumb_url") val thumbUrl: String ) {

    companion object {
        private const val VIDEO_URI = "https://www.youtube.com/watch?v="
    }

    fun appUri()
        = Uri.parse(
            String.format(
                "%s%s",
                VIDEO_URI,
                uid
            )
        )
}