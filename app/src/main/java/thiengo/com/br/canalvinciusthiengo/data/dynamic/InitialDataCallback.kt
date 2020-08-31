package thiengo.com.br.canalvinciusthiengo.data.dynamic

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import thiengo.com.br.canalvinciusthiengo.data.fixed.LastVideoData
import thiengo.com.br.canalvinciusthiengo.data.fixed.PlayListsData

/**
 * Classe responsável por inicializar a base de
 * dados local com dados de "último vídeo" e
 * principais PlayLists disponíveis.
 *
 * Desta forma o usuário seguidor do canal não
 * corre o risco de ter em mãos um aplicativo
 * "inútil" caso a cota de acesso do app ao
 * YouTube Data API já tenha estourado.
 *
 * @property context contexto do aplicativo.
 * @constructor cria um objeto completo do tipo
 * [InitialDataCallback].
 */
class InitialDataCallback(
    private val context: Context ) : RoomDatabase.Callback() {

    override fun onCreate( db: SupportSQLiteDatabase ) {
        super.onCreate( db )
        initLastVideo()
        initPlayLists()
    }

    /**
     * Inicializa a tabela LastVideo do banco de
     * dados local.
     */
    private fun initLastVideo(){
        UtilDatabase
            .getInstance( context = context )
            .saveLastVideo(
                lastVideo = LastVideoData.getInitialVideo()
            )
    }

    /**
     * Inicializa a tabela PlayList do banco de
     * dados local.
     */
    private fun initPlayLists(){
        UtilDatabase
            .getInstance( context = context )
            .savePlayLists(
                playLists = PlayListsData.getInitialPlayLists()
            )
    }
}