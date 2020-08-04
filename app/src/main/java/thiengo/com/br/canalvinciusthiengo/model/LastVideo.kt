package thiengo.com.br.canalvinciusthiengo.model

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import thiengo.com.br.canalvinciusthiengo.config.YouTubeConfig

/**
 * O último vídeo público disponível no canal
 * YouTube do aplicativo.
 *
 * O objetivo desta classe (objeto desta classe)
 * é manter os principais dados do último vídeo
 * do canal para que o usuário do app consiga
 * ter acesso imediato a ele.
 *
 * Outro objetivo desta classe é ser uma entidade
 * (estrutura) para a persistência local, Room.
 * Pois os dados do último vídeo são carregados de
 * um servidor remoto, servidor do YouTube. E com
 * a Room API é possível ainda permitir acesso do
 * usuário ao vídeo mesmo quando o servidor não
 * mais retornou respostas.
 *
 * @property uid identificador único do vídeo
 * para acesso a ele no site ou aplicativo do
 * YouTube e também na persistência local, Room
 * API. É o mesmo identificador do Vídeo no
 * site do YouTube.
 * @property title título do vídeo.
 * @property description descrição do vídeo.
 * @property uid identificador único da PlayList
 * @constructor cria um objeto completo do tipo
 * LastVideo.
 */
@Entity
data class LastVideo(
        @PrimaryKey val uid: String,
        @ColumnInfo( name = "title" ) val title: String,
        @ColumnInfo( name = "description" ) val description: String = ""
    ) : Parcelable {

    /**
     * @property thumbUrl contém a thumb URL
     * do vídeo. O método set() foi sobrescrito
     * para que sempre tenha uma URL válida de
     * thumb de vídeo.
     * @return a Web URL válida da thumb do
     * vídeo.
     */
    @ColumnInfo( name = "thumb_url" )
    var thumbUrl: String = ""
        set( value ) {
            if( value.isNotEmpty() ){
                field = value
            }
            field = alternativeThumbUrl()
        }

    /**
     * Retorna a Web URL alternativa da thumb do
     * vídeo.
     *
     * É útil principalmente quando o novo último
     * vídeo é enviado ao aplicativo por meio de
     * notificação push. Pois a notificação
     * carrega também como dado a URL do vídeo e
     * não a URL da thumb.
     *
     * @return a Web URL da thumb do vídeo.
     */
    private fun alternativeThumbUrl()
        = String.format(
            YouTubeConfig.Channel.VIDEO_THUMB_URL_TEMPLATE,
            uid
        )

    /**
     * Retorna a Web URL do vídeo. URL que deve
     * ser acionada junto a um objeto Intent em
     * uma invocação à startActivity().
     *
     * Assim o vídeo será aberto dentro do
     * aplicativo nativo do YouTube ou no site
     * oficial.
     *
     * @return a Web URL do vídeo.
     */
    fun webUri()
        = Uri.parse(
            String.format(
                YouTubeConfig.Channel.VIDEO_URL_TEMPLATE,
                uid
            )
        )

    /**
     * Todos os códigos a partir deste ponto são
     * referentes ao Parcelable que é aplicado ao
     * objeto do tipo LastVideo para que ele
     * possa ser transportado dentro de objetos
     * Intent de maneira eficiente.
     */
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
        val CREATOR: Parcelable.Creator<LastVideo>
            = object : Parcelable.Creator<LastVideo> {

            override fun createFromParcel( source: Parcel ) : LastVideo
                = LastVideo( source )

            override fun newArray( size: Int ) : Array<LastVideo?>
                = arrayOfNulls( size )
        }
    }
}