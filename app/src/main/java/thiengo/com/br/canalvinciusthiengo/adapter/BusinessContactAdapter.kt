package thiengo.com.br.canalvinciusthiengo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.domain.BusinessContact

class BusinessContactAdapter(
    val context: Context,
    val contacts: List<BusinessContact>,
    val callBusinessAppCallback: (BusinessContact)->Unit )
    : RecyclerView.Adapter<BusinessContactViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int ) : BusinessContactViewHolder {

        val layout = LayoutInflater
            .from( context )
            .inflate(
                R.layout.business_contact_item,
                parent,
                false
            )

        return BusinessContactViewHolder(
            adapter = this,
            callBusinessAppCallback = callBusinessAppCallback,
            itemView = layout
        )
    }

    override fun onBindViewHolder(
        holder: BusinessContactViewHolder,
        position: Int ){

        holder.setModel(
            contact = contacts[ position ]
        )
    }

    override fun getItemCount() = contacts.size
}