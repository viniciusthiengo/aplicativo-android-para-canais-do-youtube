package thiengo.com.br.canalvinciusthiengo.domain

import android.content.res.Resources
import android.net.Uri

interface ListItem{
    fun getIcon() : Int
    fun getMainText() : String
    fun getFirstAuxText() : String
    fun getSecondAuxText( resource: Resources) : String
    fun getWebUri() : Uri?
    fun getAppUri() : Uri?
}