package thiengo.com.br.canalvinciusthiengo.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.domain.MenuItem
import thiengo.com.br.canalvinciusthiengo.domain.MenuItemStatus

class MenuViewHolder(
        val adapter: MenuAdapter,
        val changeFragmentCallback: (MenuItem)->Unit,
        itemView: View
    ) : RecyclerView.ViewHolder( itemView ), View.OnClickListener {

    var ivIcon: ImageView
    var tvLabel: TextView

    init {
        itemView.setOnClickListener( this )

        ivIcon = itemView.findViewById( R.id.iv_icon )
        tvLabel = itemView.findViewById( R.id.tv_label )
    }

    fun setModel( item: MenuItem ) {
        tvLabel.text = item.label
        ivIcon.setImageResource( item.icon )
        ivIcon.contentDescription = item.label

        setStyle( item = item )
    }

    private fun setStyle( item: MenuItem ){
        /*
         * Até a última declaração de variável mutável (var)
         * tem toda a definição de estilo de um
         * "item não selecionado" (isSelected == false) que
         * é o valor inicial de cada item de itinerário em
         * tela.
         * */
        var tvLabelColor = R.color.colorMediumGrey
        var ivIconColor = R.color.colorMediumGrey

        if( item.isSelected == MenuItemStatus.SELECTED ){
            /*
             * A seguir toda a definição de valores de um
             * "item selecionado" (isSelected == true).
             * */
            ivIconColor = R.color.colorOrange
            tvLabelColor = R.color.colorOrange
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

    override fun onClick( view: View ) {

        val selectedItem = adapter.items.first {
            it.isSelected == MenuItemStatus.SELECTED
        }
        val indexItemSelected = adapter.items.indexOf( selectedItem )

        /*
         * Cláusula de guarda para que não haja ativação
         * redundante de fragmento já em foreground.
         * */
        if( indexItemSelected == adapterPosition ){
            return
        }

        /*
         * Garantindo que o imte selecionado anteriormente
         * não mais mantém esse status.
         *
         * Pois um novo item foi selecionado.
         * */
        selectedItem.isSelected = MenuItemStatus.NOT_SELECTED
        adapter.notifyItemChanged( indexItemSelected )

        /*
         * O algoritmo a seguir é responsável por colocar, no
         * item acionado pelo usuário, o status e estilo adequados
         * de acordo com o status atual do item.
         *
         * Com a função with() sendo utilizada nós podemos abreviar
         * o código. Dentro do bloco desta função podemos utilizar
         * "this" ao invés de "adapter.items[ adapterPosition ]".
         * */
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