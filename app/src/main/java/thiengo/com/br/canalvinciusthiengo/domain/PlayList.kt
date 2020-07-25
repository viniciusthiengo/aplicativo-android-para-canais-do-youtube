package thiengo.com.br.canalvinciusthiengo.domain

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import thiengo.com.br.canalvinciusthiengo.config.YouTubeConfig

@Entity
class PlayList(
    @ColumnInfo( name = "title" ) val title: String,
    @PrimaryKey val uid: String ){

    fun appUri()
        = Uri.parse(
            String.format(
                YouTubeConfig.Channel.PLAYLIST_URL_TEMPLATE,
                uid
            )
        )
}