package thiengo.com.br.canalvinciusthiengo.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.domain.Group


class GroupViewHolder(
    val adapter: GroupAdapter,
    val callGroupAppCallback: (Group)->Unit,
    itemView: View ) : RecyclerView.ViewHolder( itemView ), View.OnClickListener {

    var ivLogo: ImageView
    var tvName: TextView

    init {
        itemView.setOnClickListener( this )

        ivLogo = itemView.findViewById( R.id.iv_logo )
        tvName = itemView.findViewById( R.id.tv_name )
    }

    fun setModel( group: Group ) {

        ivLogo.setImageResource( group.logo )
        ivLogo.contentDescription = group.labelToItem()

        tvName.text = group.labelToItem()
    }

    override fun onClick( view: View ) {

        callGroupAppCallback(
            adapter.groups[ adapterPosition ]
        )
    }
}