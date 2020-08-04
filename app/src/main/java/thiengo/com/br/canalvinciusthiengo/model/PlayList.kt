package thiengo.com.br.canalvinciusthiengo.model

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.config.YouTubeConfig

/**
 * Uma PlayList disponível no canal YouTube vinculado
 * ao aplicativo.
 *
 * O objetivo desta classe (objetos desta classe)
 * é manter os principais dados da PlayList para
 * que o usuário do app consiga ter acesso imediato
 * a ela.
 *
 * Outro objetivo desta classe é ser uma entidade
 * (estrutura) para a persistência local, Room.
 * Pois os dados de PlayList são carregados de um
 * servidor remoto, servidor do YouTube. E com a
 * Room API é possível ainda permitir acesso do
 * usuário à PlayList mesmo quando o servidor não
 * mais retornou respostas.
 *
 * @property title nome da PlayList.
 * @property uid identificador único da PlayList
 * para acesso a ela no site ou aplicativo do
 * YouTube e também na persistência local, Room
 * API. É o mesmo identificador da PlayList no
 * site do YouTube.
 * @constructor cria um objeto completo do tipo
 * PlayList.
 */
@Entity
class PlayList(
        @ColumnInfo( name = "title" ) val title: String,
        @PrimaryKey val uid: String,
        val thumb: Int = R.drawable.ic_playlist_color
    ) : ListItem {

    override fun getMainText()
        = title

    override fun getAppUri()
        = Uri.parse(
            String.format(
                YouTubeConfig.Channel.PLAYLIST_URL_TEMPLATE,
                uid
            )
        )

    override fun getIcon()
        = thumb
}