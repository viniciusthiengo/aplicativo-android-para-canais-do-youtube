package thiengo.com.br.canalvinciusthiengo.domain

import android.content.res.Resources
import android.net.Uri
import thiengo.com.br.canalvinciusthiengo.R

class Course(
    val title: String,
    val categories: List<String>,
    private val amountVideos: Int,
    private val webPage: String ) : ListItem{

    fun amountVideosAsItemLabel( resource: Resources )
        = String.format(
            resource.getString( R.string.complement_amount_videos ),
            amountVideos
        )

    fun categoriesAsItemLabel()
        = categories.joinToString( ", " )

    fun webPageUri()
        = Uri.parse( webPage )


    override fun getIcon()
        = R.drawable.ic_courses_color

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

    override fun getAppUri()
        = null
}