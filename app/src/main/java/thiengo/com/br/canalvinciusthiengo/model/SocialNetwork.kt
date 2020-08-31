package thiengo.com.br.canalvinciusthiengo.model

import android.net.Uri

/**
 * Uma rede social vinculada ao canal YouTube do
 * aplicativo.
 *
 * O objetivo desta classe (objetos desta classe)
 * é manter os dados que permitem acesso imediato a
 * uma outra rede social (ou site, ou aplicativo,
 * ...) que está vinculada ao canal YouTube do app.
 *
 * @property network nome da rede (aplicativo, site,
 * ...) vinculada ao canal.
 * @property accountName nome da conta na rede
 * vinculada ao canal.
 * @property appUri [Intent] URI da conta na rede
 * vinculada ao canal.
 * @property webUri URL da conta na rede vinculada
 * ao canal.
 * @property logo ícone do aplicativo da rede
 * vinculada ao canal.
 * @constructor cria um objeto completo do tipo
 * [SocialNetwork].
 */
class SocialNetwork(
    val network: String,
    private val accountName: String,
    private val appUri: String,
    private val webUri: String,
    private val logo: Int ) : ListItem {

    override fun getMainText()
        = String.format(
            "%s: %s",
            network,
            accountName
        )

    override fun getWebUri()
        = Uri.parse( webUri )

    override fun getAppUri()
        = Uri.parse( appUri )

    override fun getIcon()
        = logo
}