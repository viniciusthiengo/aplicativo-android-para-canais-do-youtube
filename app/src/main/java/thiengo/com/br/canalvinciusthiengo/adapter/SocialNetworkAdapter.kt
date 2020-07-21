package thiengo.com.br.canalvinciusthiengo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.domain.SocialNetwork

class SocialNetworkAdapter(
        val context: Context,
        val networks: List<SocialNetwork>,
        val callNetworkAppCallback: (SocialNetwork)->Unit
    ) : RecyclerView.Adapter<SocialNetworkViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int ) : SocialNetworkViewHolder {

        val layout = LayoutInflater
            .from( context )
            .inflate(
                R.layout.social_network_item,
                parent,
                false
            )

        return SocialNetworkViewHolder(
            adapter = this,
            callNetworkAppCallback = callNetworkAppCallback,
            itemView = layout
        )
    }

    override fun onBindViewHolder(
        holder: SocialNetworkViewHolder,
        position: Int ){

        holder.setModel(
            network = networks[ position ]
        )
    }

    override fun getItemCount() = networks.size
}