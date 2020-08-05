package thiengo.com.br.canalvinciusthiengo.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.model.MenuItem
import thiengo.com.br.canalvinciusthiengo.model.MenuItemStatus

/**
 * Classe responsável por aplicar o padrão
 * ViewHolder na lista de itens de menu principal
 * do app.
 *
 * @property adapter adaptador de itens de menu.
 * @property changeFragmentCallback contém o
 * algoritmo de execução quando um novo item é
 * acionado pelo usuário.
 * @property itemView layout de item.
 * @constructor cria um objeto completo do tipo
 * MenuViewHolder.
 */
class MenuViewHolder(
        private val adapter: MenuAdapter,
        private val changeFragmentCallback: (MenuItem)->Unit,
        itemView: View
    ) : RecyclerView.ViewHolder( itemView ), View.OnClickListener {

    /**
     * Propriedades de layout, UI.
     */
    var ivIcon: ImageView
    var tvLabel: TextView

    /**
     * Bloco de inicialização da UI do layout
     * de item de menu.
     */
    init {
        itemView.setOnClickListener( this )

        ivIcon = itemView.findViewById( R.id.iv_icon )
        tvLabel = itemView.findViewById( R.id.tv_label )
    }

    /**
     * Define em UI os dados que devem ser
     * apresentados em lista.
     *
     * @param item item de menu.
     */
    fun setModel( item: MenuItem ) {
        tvLabel.text = item.label
        ivIcon.setImageResource( item.icon )
        ivIcon.contentDescription = item.label

        setStyle( item = item )
    }

    /**
     * Define o estilo correto do ícone e do
     * texto do item de menu. Estilo correto de
     * acordo com o estado dele: selecionado;
     * ou não selecionado.
     *
     * @param item item de menu.
     */
    private fun setStyle( item: MenuItem ){

        var itemColor = R.color.colorMenuItemNotSelected

        if( item.isSelected == MenuItemStatus.SELECTED ){
            itemColor = R.color.colorMenuItemSelected
        }

        val color = ContextCompat.getColor(
            adapter.context,
            itemColor
        )

        ivIcon.setColorFilter( color )
        tvLabel.setTextColor( color )
    }

    override fun onClick( view: View ){

        val oldSelectedItem = adapter.items.first {
            it.isSelected == MenuItemStatus.SELECTED
        }
        val indexOldSelectedItem = adapter.items.indexOf( oldSelectedItem )

        /**
         * Cláusula de guarda para que não haja
         * ativação redundante de fragmento já
         * em foreground.
         */
        if( indexOldSelectedItem == adapterPosition ){
            return
        }

        /**
         * Garantindo que o item selecionado
         * anteriormente tenha agora o status de
         * item "não selecionado". Pois um novo
         * item foi acionado.
         */
        oldSelectedItem.apply {
            isSelected = MenuItemStatus.NOT_SELECTED
        }
        adapter.notifyItemChanged( indexOldSelectedItem )

        /**
         * O algoritmo a seguir é responsável por
         * colocar, no item acionado pelo usuário,
         * o status e estilo adequados.
         */
        adapter.items[ adapterPosition ].apply {
            isSelected = MenuItemStatus.SELECTED
        }
        adapter.notifyItemChanged(
            adapterPosition
        )

        changeFragmentCallback(
            adapter.items[adapterPosition]
        )
    }
}