package thiengo.com.br.canalvinciusthiengo.data.dynamic

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import thiengo.com.br.canalvinciusthiengo.model.LastVideo
import thiengo.com.br.canalvinciusthiengo.model.PlayList

/**
 * Classe de banco de dados local. Com todas as
 * configurações necessárias para atender às
 * necessidades de projeto.
 *
 * Para que qualquer atualização em estrutura
 * de banco de dados local tenha efeito é
 * preciso mudar a versão da base em "version"
 * na anotação @Database.
 */
@Database(
    entities = arrayOf(
        LastVideo::class,
        PlayList::class
    ),
    version = 19
)
abstract class ChannelDatabase: RoomDatabase() {

    companion object{
        /**
         * Constante com o nome do banco de
         * dados local.
         */
        private const val DB_NAME = "youtube-channel"

        /**
         * Constante responsável por conter a
         * única instância de ChannelDatabase
         * disponível durante toda a execução
         * de cada chamada a banco de dados
         * local.
         */
        private var instance: ChannelDatabase? = null

        /**
         * Método que aplica, junto à propriedade
         * instance, o padrão Singleton em classe.
         * Garantindo que somente uma instância de
         * ChannelDatabase estará disponível durante
         * toda a execução de cada chamada a banco
         * de dados local. Ajudando também a
         * diminuir a possibilidade de vazamento
         * de memória e de locks de tabela.
         *
         * @param context contexto do aplicativo.
         * @return instância única de ChannelDatabase.
         */
        fun getInstance( context: Context ) : ChannelDatabase {

            if( instance == null || !instance!!.isOpen ){

                instance = Room.databaseBuilder(
                    context,
                    ChannelDatabase::class.java,
                    DB_NAME
                )
                .addCallback(
                    InitialDataCallback(
                        context = context
                    )
                )
                .fallbackToDestructiveMigration()
                .build()
            }
            return instance!!
        }
    }

    abstract fun lastVideoDao() : LastVideoDao
    abstract fun playListDao() : PlayListDao
}