package thiengo.com.br.canalvinciusthiengo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.domain.ListItem

class ListItemAdapter(
    val context: Context,
    val items: List<ListItem>,
    val callExternalAppCallback: (ListItem)->Unit )
    : RecyclerView.Adapter<ListItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int ) : ListItemViewHolder {

        val layout = LayoutInflater
            .from( context )
            .inflate(
                R.layout.list_item,
                parent,
                false
            )

        return ListItemViewHolder(
            adapter = this,
            callExternalAppCallback = callExternalAppCallback,
            itemView = layout
        )
    }

    override fun onBindViewHolder(
        holder: ListItemViewHolder,
        position: Int ){

        holder.setModel(
            item = items[ position ]
        )
    }

    override fun getItemCount() = items.size
}