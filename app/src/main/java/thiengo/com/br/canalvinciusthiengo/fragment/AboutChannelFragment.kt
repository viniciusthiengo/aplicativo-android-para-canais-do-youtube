package thiengo.com.br.canalvinciusthiengo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import thiengo.com.br.canalvinciusthiengo.R


class AboutChannelFragment : Fragment() {

    companion object{
        const val KEY = "AboutChannelFragment_key"
    }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

        return inflater.inflate(
            R.layout.fragment_about_channel,
            container,
            false
        )
    }
}