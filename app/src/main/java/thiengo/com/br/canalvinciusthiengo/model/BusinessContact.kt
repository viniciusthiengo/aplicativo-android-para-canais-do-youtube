package thiengo.com.br.canalvinciusthiengo.model

import android.net.Uri

/**
 * Um contato de negócio com o canal YouTube
 * vinculado ao aplicativo. Por aqui é possível
 * fechar acordos comerciais com o canal.
 *
 * O objetivo desta classe (objetos desta classe)
 * é manter todos os dados importantes para o
 * imediato contato entre (possível) cliente e
 * proprietário do canal YouTube.
 *
 * @property place nome do aplicativo Android
 * para contato.
 * @property contact endereço de contato.
 * @property webUri URL do endereço de
 * contato no app / local de acesso a ele.
 * @property logo ícone que identifica o
 * app / local Android para contato.
 * @constructor cria um objeto completo do tipo
 * BusinessContact.
 */
class BusinessContact(
    val place: String,
    val contact: String,
    private val webUri: String,
    private val logo: Int ) : ListItem {

    override fun getMainText()
        = String.format(
            "%s: %s",
            place,
            contact
        )

    override fun getWebUri()
        = Uri.parse( webUri )

    override fun getIcon()
        = logo
}