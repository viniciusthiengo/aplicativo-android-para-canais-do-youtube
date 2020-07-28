package thiengo.com.br.canalvinciusthiengo.model

import android.net.Uri

/**
 * Um grupo que é administrado ou apenas indicado
 * pelo proprietário do canal YouTube vinculado
 * ao aplicativo.
 *
 * O objetivo desta classe (objetos desta classe)
 * é manter os principais dados do grupo para que
 * o seguidor do canal consiga avalia-lo antes
 * mesmo de acessa-lo e assim decidir se vai ou
 * não fazer parte dele.
 *
 * @property place local digital, aplicativo
 * Android, do grupo.
 * @property name nome do grupo.
 * @property appUri Intent URI do grupo no
 * aplicativo de acesso a ele.
 * @property logo ícone de identificação do
 * aplicativo do grupo.
 * @constructor cria um objeto completo do tipo
 * Group.
 */
class Group(
    val place: String,
    val name: String,
    private val appUri: String,
    private val logo: Int ) : ListItem {

    override fun getMainText()
        = String.format(
            "%s: %s",
            place,
            name
        )

    override fun getAppUri()
        = Uri.parse( appUri )

    override fun getIcon()
        = logo
}