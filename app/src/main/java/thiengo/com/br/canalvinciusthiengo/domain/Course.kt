package thiengo.com.br.canalvinciusthiengo.domain

import android.content.res.Resources
import android.net.Uri
import thiengo.com.br.canalvinciusthiengo.R

class Course(
        val title: String,
        val categories: List<String>,
        private val amountVideos: Int,
        private val webPage: String
    ) {

    fun amountVideosAsItemLabel( resource: Resources )
        = String.format(
            resource.getString( R.string.complement_amount_videos ),
            amountVideos
        )

    fun categoriesAsItemLabel()
        = categories.joinToString( ", " )

    fun webPageUri()
        = Uri.parse( webPage )
}