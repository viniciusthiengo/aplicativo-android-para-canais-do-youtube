package thiengo.com.br.canalvinciusthiengo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.domain.Book

class BookAdapter(
    val context: Context,
    val books: List<Book>,
    val callBookPageCallback: (Book)->Unit )
    : RecyclerView.Adapter<BookViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int ) : BookViewHolder {

        val layout = LayoutInflater
            .from( context )
            .inflate(
                R.layout.book_item,
                parent,
                false
            )

        return BookViewHolder(
            adapter = this,
            callBookPageCallback = callBookPageCallback,
            itemView = layout
        )
    }

    override fun onBindViewHolder(
        holder: BookViewHolder,
        position: Int ){

        holder.setModel(
            book = books[ position ]
        )
    }

    override fun getItemCount() = books.size
}