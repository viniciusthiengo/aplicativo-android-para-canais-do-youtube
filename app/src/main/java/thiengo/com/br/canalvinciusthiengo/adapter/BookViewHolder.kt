package thiengo.com.br.canalvinciusthiengo.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.domain.Book


class BookViewHolder(
    val adapter: BookAdapter,
    val callBookPageCallback: (Book)->Unit,
    itemView: View ) : RecyclerView.ViewHolder( itemView ), View.OnClickListener {

    var tvTitle: TextView
    var tvCategories: TextView

    init {
        itemView.setOnClickListener( this )

        tvTitle = itemView.findViewById( R.id.tv_title )
        tvCategories = itemView.findViewById( R.id.tv_categories )
    }

    fun setModel( book: Book ) {

        tvTitle.text = book.title
        tvCategories.text = book.categoriesAsItemLabel()
    }

    override fun onClick( view: View ) {

        callBookPageCallback(
            adapter.books[ adapterPosition ]
        )
    }
}