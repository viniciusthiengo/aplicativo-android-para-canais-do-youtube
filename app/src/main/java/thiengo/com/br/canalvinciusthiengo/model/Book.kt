package thiengo.com.br.canalvinciusthiengo.model

import android.net.Uri

/**
 * Um livro autoral ou apenas indicado pelo
 * proprietário do canal YouTube vinculado ao
 * aplicativo.
 *
 * O objetivo desta classe (objetos desta classe)
 * é apenas manter os dados importantes para
 * apresentação do livro vinculado ao canal
 * YouTube do aplicativo.
 *
 * @property title título do livro.
 * @property categories categorias do livro.
 * @property webPage URL da página Web do livro.
 * @property cover identificador único do ícone
 * que simula a capa do livro.
 * @constructor cria um objeto completo do tipo
 * Book.
 */
class Book(
    val title: String,
    private val categories: List<String>,
    private val webPage: String,
    private val cover: Int ) : ListItem {

    override fun getMainText()
        = title

    override fun getFirstAuxText()
        = categories.joinToString(", ")

    override fun getWebUri()
        = Uri.parse( webPage )

    override fun getIcon()
        = cover
}