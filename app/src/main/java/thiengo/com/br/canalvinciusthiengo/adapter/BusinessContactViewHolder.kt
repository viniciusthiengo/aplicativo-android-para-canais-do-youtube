package thiengo.com.br.canalvinciusthiengo.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.domain.BusinessContact


class BusinessContactViewHolder(
    val adapter: BusinessContactAdapter,
    val callBusinessAppCallback: (BusinessContact)->Unit,
    itemView: View ) : RecyclerView.ViewHolder( itemView ), View.OnClickListener {

    var ivLogo: ImageView
    var tvContact: TextView

    init {
        itemView.setOnClickListener( this )

        ivLogo = itemView.findViewById( R.id.iv_cover )
        tvContact = itemView.findViewById( R.id.tv_contact )
    }

    fun setModel( contact: BusinessContact ) {

        ivLogo.setImageResource( contact.logo )
        ivLogo.contentDescription = contact.labelToItem()

        tvContact.text = contact.labelToItem()
    }

    override fun onClick( view: View ) {

        callBusinessAppCallback(
            adapter.contacts[ adapterPosition ]
        )
    }
}