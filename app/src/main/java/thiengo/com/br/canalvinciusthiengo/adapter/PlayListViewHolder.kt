package thiengo.com.br.canalvinciusthiengo.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.domain.PlayList


class PlayListViewHolder(
        val adapter: PlayListAdapter,
        val callYouTubePlayListCallback: (PlayList)->Unit,
        itemView: View
    ) : RecyclerView.ViewHolder( itemView ), View.OnClickListener {

    var tvLabel: TextView

    init {
        itemView.setOnClickListener( this )
        tvLabel = itemView.findViewById( R.id.tv_label )
    }

    fun setModel( playList: PlayList ) {
        tvLabel.text = playList.title
    }

    override fun onClick( view: View ) {

        callYouTubePlayListCallback(
            adapter.playLists[ adapterPosition ]
        )
    }
}