package thiengo.com.br.canalvinciusthiengo.data

import android.content.Context
import thiengo.com.br.canalvinciusthiengo.domain.LastVideo
import thiengo.com.br.canalvinciusthiengo.domain.PlayList
import kotlin.concurrent.thread

class UtilDatabase private constructor(
        private val appContext: Context
    ){

    companion object{
        /*
         * Padrão Singleton.
         * */
        private var instance: UtilDatabase? = null

        fun getInstance( context: Context ) : UtilDatabase {
            if( instance == null ){
                instance = UtilDatabase( appContext = context )
            }
            return instance!!
        }
    }

    private fun getDatabase() : ChannelDatabase
        = ChannelDatabase.getInstance( context = appContext )

    fun saveLastVideo( lastVideo: LastVideo ){
        thread{
            try {
                val dataBase = getDatabase()

                dataBase
                    .lastVideoDao()
                    .insert(
                        lastVideo = lastVideo
                    )

                dataBase.close()
            }
            catch( e :Exception ){}
        }
    }

    fun getLastVideo( callback: (LastVideo?)->Unit ){
        thread {
            try {
                val dataBase = getDatabase()
                val lastVideo = dataBase.lastVideoDao().get()
                dataBase.close()

                callback( lastVideo )
            }
            catch( e :Exception ){}
        }
    }

    fun savePlayLists( playLists: List<PlayList> ){
        thread{
            try {
                val dataBase = getDatabase()

                dataBase
                    .playListDao()
                    .insertAll(
                        playLists = playLists
                    )

                dataBase.close()
            }
            catch( e :Exception ){}
        }
    }

    fun getAllPlayLists( callback: (List<PlayList>?)->Unit ){

        thread {
            try {
                val dataBase = getDatabase()
                val playLists = dataBase.playListDao().getAll()
                dataBase.close()

                callback( playLists )
            }
            catch( e :Exception ){}
        }
    }

}