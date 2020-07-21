package thiengo.com.br.canalvinciusthiengo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.domain.Group

class GroupAdapter(
    val context: Context,
    val groups: List<Group>,
    val callGroupAppCallback: (Group)->Unit )
    : RecyclerView.Adapter<GroupViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int ) : GroupViewHolder {

        val layout = LayoutInflater
            .from( context )
            .inflate(
                R.layout.group_item,
                parent,
                false
            )

        return GroupViewHolder(
            adapter = this,
            callGroupAppCallback = callGroupAppCallback,
            itemView = layout
        )
    }

    override fun onBindViewHolder(
        holder: GroupViewHolder,
        position: Int ){

        holder.setModel(
            group = groups[ position ]
        )
    }

    override fun getItemCount() = groups.size
}