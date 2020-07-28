package thiengo.com.br.canalvinciusthiengo.model

import android.content.res.Resources
import android.net.Uri
import thiengo.com.br.canalvinciusthiengo.R

/**
 * Um curso ou apenas uma indicação de curso pelo
 * proprietário do canal que está vinculado ao
 * app.
 *
 * O objetivo desta classe (objetos desta classe)
 * é apenas manter os dados importantes para
 * apresentação do curso vinculado ao canal
 * YouTube do aplicativo.
 *
 * @property title título do curso.
 * @property categories categorias do curso.
 * @property amountVideos quantidade de vídeo
 * aulas presentes no curso.
 * @property webPage URL da página Web do curso.
 * @constructor cria um objeto completo do tipo
 * Course.
 */
class Course(
    val title: String,
    private val categories: List<String>,
    private val amountVideos: Int,
    private val webPage: String ) : ListItem {


    override fun getMainText()
        = title

    override fun getFirstAuxText()
        = categories.joinToString( ", " )

    override fun getSecondAuxText( resource: Resources )
        = String.format(
            resource.getString( R.string.complement_amount_videos ),
            amountVideos
        )

    override fun getWebUri()
        = Uri.parse( webPage )

    override fun getIcon()
            = R.drawable.ic_courses_color
}