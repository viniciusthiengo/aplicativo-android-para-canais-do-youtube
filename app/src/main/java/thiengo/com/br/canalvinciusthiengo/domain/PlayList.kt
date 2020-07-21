package thiengo.com.br.canalvinciusthiengo.domain

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PlayList(
        @ColumnInfo( name = "title" ) val title: String,
        @PrimaryKey val uid: String
    ) {

    companion object{
        private const val PLAYLIST_URI = "https://www.youtube.com/playlist?list="
    }

    fun appUri()
        = Uri.parse(
            String.format(
                "%s%s",
                PLAYLIST_URI,
                uid
            )
        )
}