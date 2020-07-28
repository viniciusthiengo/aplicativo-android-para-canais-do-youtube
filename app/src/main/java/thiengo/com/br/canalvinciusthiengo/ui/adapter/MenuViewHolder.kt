package thiengo.com.br.canalvinciusthiengo.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
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

        var tvLabelColor = R.color.colorMenuItemNotSelected
        var ivIconColor = R.color.colorMenuItemNotSelected

        if( item.isSelected == MenuItemStatus.SELECTED ){
            ivIconColor = R.color.colorMenuItemSelected
            tvLabelColor = R.color.colorMenuItemSelected
        }

        ivIcon.setColorFilter(
            ContextCompat.getColor(
                adapter.context,
                ivIconColor
            )
        )
        tvLabel.setTextColor(
            ResourcesCompat.getColor(
                adapter.context.resources,
                tvLabelColor,
                null
            )
        )
    }

    override fun onClick( view: View ){

        val selectedItem = adapter.items.first {
            it.isSelected == MenuItemStatus.SELECTED
        }
        val indexItemSelected = adapter.items.indexOf( selectedItem )

        /**
         * Cláusula de guarda para que não haja
         * ativação redundante de fragmento já
         * em foreground.
         */
        if( indexItemSelected == adapterPosition ){
            return
        }

        /**
         * Garantindo que o item selecionado
         * anteriormente tenha agora o status de
         * item "não selecionado". Pois um novo
         * item foi acionado.
         */
        selectedItem.isSelected = MenuItemStatus.NOT_SELECTED
        adapter.notifyItemChanged( indexItemSelected )

        /**
         * O algoritmo a seguir é responsável por
         * colocar, no item acionado pelo usuário,
         * o status e estilo adequados de acordo
         * com o status atual do item.
         *
         * Com a função with() sendo utilizada nós
         * podemos abreviar o código. Dentro do
         * bloco desta função podemos utilizar "this"
         * ao invés de "adapter.items[ adapterPosition ]".
         */
        with( adapter.items[ adapterPosition ] ){

            this.isSelected = when( this.isSelected ){
                MenuItemStatus.SELECTED ->
                    MenuItemStatus.NOT_SELECTED
                else ->
                    MenuItemStatus.SELECTED
            }
        }
        adapter.notifyItemChanged(
            adapterPosition
        )

        changeFragmentCallback(
            adapter.items[adapterPosition]
        )
    }
}