package thiengo.com.br.canalvinciusthiengo.data

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import thiengo.com.br.canalvinciusthiengo.domain.LastVideo
import thiengo.com.br.canalvinciusthiengo.fragment.LastVideoFragment

class NewLastVideoBroadcast(
    private val fragment: LastVideoFragment ): BroadcastReceiver() {

    companion object{
        const val FILTER_KEY = "LocalBroadcastLastVideo_key"
        const val DATA_KEY = "LastVideo_key"
    }

    override fun onReceive( context: Context, data: Intent ) {
        val lastVideo = data.getParcelableExtra<LastVideo>( DATA_KEY )!!

        fragment.newLastVideoData( lVideo = lastVideo )
    }
}