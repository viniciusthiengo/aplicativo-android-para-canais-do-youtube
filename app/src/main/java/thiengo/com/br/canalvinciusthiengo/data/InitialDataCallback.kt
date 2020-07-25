package thiengo.com.br.canalvinciusthiengo.data

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import thiengo.com.br.canalvinciusthiengo.domain.LastVideoData
import thiengo.com.br.canalvinciusthiengo.domain.PlayListData

class InitialDataCallback(
    val context: Context ) : RoomDatabase.Callback() {

    override fun onCreate( db: SupportSQLiteDatabase ) {
        super.onCreate( db )

        initLastVideo()
        initPlayLists()
    }

    private fun initLastVideo(){
        UtilDatabase
            .getInstance( context = context )
            .saveLastVideo(
                lastVideo = LastVideoData.getInitialVideo()
            )
    }

    private fun initPlayLists(){
        UtilDatabase
            .getInstance( context = context )
            .savePlayLists(
                playLists = PlayListData.getInitialPlayLists()
            )
    }
}