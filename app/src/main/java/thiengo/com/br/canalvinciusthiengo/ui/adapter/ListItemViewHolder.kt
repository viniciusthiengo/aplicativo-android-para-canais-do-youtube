package thiengo.com.br.canalvinciusthiengo.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.model.ListItem

/**
 * Classe responsável por aplicar o padrão
 * ViewHolder nas listas de itens dos fragmentos
 * que contêm o framework [RecyclerView].
 *
 * @property adapter adaptador de itens de lista.
 * @property callExternalAppCallback contém o
 * algoritmo de execução quando um novo item é
 * acionado pelo usuário.
 * @property itemView layout de item.
 * @constructor cria um objeto completo do tipo
 * [ListItemViewHolder].
 */
class ListItemViewHolder(
        private val adapter: ListItemAdapter,
        private val callExternalAppCallback: (ListItem)->Unit,
        itemView: View
    ) : RecyclerView.ViewHolder( itemView ), View.OnClickListener {

    /**
     * Propriedades de layout, UI.
     */
    private var ivIcon: ImageView
    private var tvMainText: TextView
    private var tvFirstAuxText: TextView
    private var tvSecondAuxText: TextView

    /**
     * Bloco de inicialização da UI do layout
     * de item de lista.
     */
    init {
        itemView.setOnClickListener( this )

        ivIcon = itemView.findViewById( R.id.iv_icon )
        tvMainText = itemView.findViewById( R.id.tv_main_text )
        tvFirstAuxText = itemView.findViewById( R.id.tv_first_aux_text )
        tvSecondAuxText = itemView.findViewById( R.id.tv_second_aux_text )
    }

    /**
     * Define em UI os dados que devem ser
     * apresentados em item lista.
     *
     * @param item item de lista.
     */
    fun setModel( item: ListItem ) {
        ivIcon.setImageResource( item.getIcon() )
        ivIcon.contentDescription = item.getMainText()

        tvMainText.text = item.getMainText()
        setAuxTexts( item = item )
    }

    /**
     * Define em UI quais textos auxiliares
     * de item de lista devem ou não aparecer
     * em layout.
     *
     * @param item item de lista.
     */
    private fun setAuxTexts( item: ListItem ){

        if( item.getFirstAuxText().isNotEmpty() ){
            tvFirstAuxText.text = item.getFirstAuxText()
            tvFirstAuxText.visibility = View.VISIBLE

            val secondText = item.getSecondAuxText(
                resource = adapter.context.resources
            )

            if( secondText.isNotEmpty() ){
                tvSecondAuxText.text = secondText
                tvSecondAuxText.visibility = View.VISIBLE
            }
            else{
                tvSecondAuxText.visibility = View.GONE
            }
        }
        else{
            tvFirstAuxText.visibility = View.GONE
            tvSecondAuxText.visibility = View.GONE
        }
    }

    override fun onClick( view: View ) {
        callExternalAppCallback(
            adapter.items[ adapterPosition ]
        )
    }
}