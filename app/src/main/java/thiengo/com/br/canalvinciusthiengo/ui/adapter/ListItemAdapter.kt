package thiengo.com.br.canalvinciusthiengo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.model.ListItem

/**
 * Classe adaptadora de itens com lógica comum a
 * todos os fragmentos do app que têm como parte
 * do conteúdo o framework de lista [RecyclerView].
 *
 * Define qual ViewHolder, qual lista de dados e
 * qual layout será utilizado no framework de
 * lista do fragmento.
 *
 * @property context contexto do aplicativo.
 * @property items itens de lista do tipo (que
 * implementem de) [ListItem].
 * @property callExternalAppCallback contém o
 * algoritmo de execução quando um item é
 * acionado pelo usuário.
 * @constructor cria um objeto completo do tipo
 * [ListItemAdapter].
 */
class ListItemAdapter(
        val context: Context,
        val items: List<ListItem>,
        private val callExternalAppCallback: (ListItem)->Unit
    ) : RecyclerView.Adapter<ListItemViewHolder>() {

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

    override fun getItemCount()
        = items.size
}