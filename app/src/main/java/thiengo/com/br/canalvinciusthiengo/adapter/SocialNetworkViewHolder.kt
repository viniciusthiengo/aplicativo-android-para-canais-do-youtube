package thiengo.com.br.canalvinciusthiengo.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.domain.SocialNetwork


class SocialNetworkViewHolder(
        val adapter: SocialNetworkAdapter,
        val callNetworkAppCallback: (SocialNetwork)->Unit,
        itemView: View
    ) : RecyclerView.ViewHolder( itemView ), View.OnClickListener {

    var ivLogo: ImageView
    var tvAccountName: TextView

    init {
        itemView.setOnClickListener( this )

        ivLogo = itemView.findViewById( R.id.iv_cover )
        tvAccountName = itemView.findViewById( R.id.tv_account_name )
    }

    fun setModel( network: SocialNetwork ) {

        ivLogo.setImageResource( network.logo )
        ivLogo.contentDescription = network.labelToItem()

        tvAccountName.text = network.labelToItem()
    }

    override fun onClick( view: View ) {

        callNetworkAppCallback(
            adapter.networks[ adapterPosition ]
        )
    }
}