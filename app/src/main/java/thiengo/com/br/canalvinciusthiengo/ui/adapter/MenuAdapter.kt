package thiengo.com.br.canalvinciusthiengo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.model.MenuItem

/**
 * Classe adaptadora de itens do menu principal do
 * aplicativo.
 *
 * Define qual ViewHolder, qual lista de dados e
 * qual layout será utilizado no framework de
 * lista [RecyclerView] do BottomMenu do app.
 *
 * @property context contexto do aplicativo.
 * @property items itens de menu (dados em lista).
 * @property changeFragmentCallback contém o
 * algoritmo de execução quando um novo item é
 * acionado pelo usuário.
 * @constructor cria um objeto completo do tipo
 * [MenuAdapter].
 */
class MenuAdapter(
        val context: Context,
        val items: List<MenuItem>,
        private val changeFragmentCallback: (MenuItem)->Unit
    ) : RecyclerView.Adapter<MenuViewHolder>() {

    companion object{
        /**
         * Constante que define o número de
         * colunas do menu. No caso é apenas uma
         * coluna para o menu principal em
         * apresentação horizontal.
         */
        const val NUMBER_COLUMNS = 1
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int ) : MenuViewHolder {

        val layout = LayoutInflater
            .from( context )
            .inflate(
                R.layout.menu_item,
                parent,
                false
            )

        return MenuViewHolder(
            adapter = this,
            changeFragmentCallback = changeFragmentCallback,
            itemView = layout
        )
    }

    override fun onBindViewHolder(
        holder: MenuViewHolder,
        position: Int ){

        holder.setModel(
            item = items[ position ]
        )
    }

    override fun getItemCount()
        = items.size
}