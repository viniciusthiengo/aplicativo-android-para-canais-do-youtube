package thiengo.com.br.canalvinciusthiengo.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_books.*
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.adapter.BookAdapter
import thiengo.com.br.canalvinciusthiengo.domain.Book
import thiengo.com.br.canalvinciusthiengo.domain.BooksData


class BooksFragment : Fragment() {

    companion object{
        const val KEY = "BooksFragment_key"
    }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

        return inflater.inflate(
            R.layout.fragment_books,
            container,
            false
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated( savedInstanceState )

        initBooksList()
    }

    private fun initBooksList(){

        val layoutManager = LinearLayoutManager( activity )
        rv_books.layoutManager = layoutManager

        rv_books.setHasFixedSize( true )
        rv_books.adapter = BookAdapter(
            context = activity!!,
            books = BooksData.getBooks(),
            callBookPageCallback = {
                book -> callBookPageCallback( book )
            }
        )
    }

    private fun callBookPageCallback( book: Book){

        val intent = Intent(
            Intent.ACTION_VIEW,
            book.webPageUri()
        )

        /*
         * É utópico, mas pode ocorrer de não haver  um
         * navegador Web no aparelho do usuário que abra a URL da
         * página do livro acionado.
         *
         * Sendo assim, ao invés de gerar uma exceção, nós
         * avisamos ao usuário a necessidade de instalar o
         * aplicativo adequado.
         * */
        if( intent.resolveActivity( activity!!.packageManager ) == null ){
            Toast
                .makeText(
                    activity,
                    String.format(
                        getString( R.string.books_toast_alert ),
                        book.title
                    ),
                    Toast.LENGTH_LONG
                )
                .show()

            return
        }

        activity!!.startActivity( intent )
    }
}