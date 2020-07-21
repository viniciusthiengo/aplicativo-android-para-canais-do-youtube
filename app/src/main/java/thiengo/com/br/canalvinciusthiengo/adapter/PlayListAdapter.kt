package thiengo.com.br.canalvinciusthiengo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.domain.PlayList

class PlayListAdapter(
    val context: Context,
    val playLists: List<PlayList>,
    val callYouTubePlayListCallback: (PlayList)->Unit )
    : RecyclerView.Adapter<PlayListViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int ) : PlayListViewHolder {

        val layout = LayoutInflater
            .from( context )
            .inflate(
                R.layout.play_list_item,
                parent,
                false
            )

        return PlayListViewHolder(
            adapter = this,
            callYouTubePlayListCallback = callYouTubePlayListCallback,
            itemView = layout
        )
    }

    override fun onBindViewHolder(
        holder: PlayListViewHolder,
        position: Int ){

        holder.setModel(
            playList = playLists[ position ]
        )
    }

    override fun getItemCount() = playLists.size
}