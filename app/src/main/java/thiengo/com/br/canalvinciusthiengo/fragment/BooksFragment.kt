package thiengo.com.br.canalvinciusthiengo.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_framework_list.*
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.adapter.BookAdapter
import thiengo.com.br.canalvinciusthiengo.adapter.BusinessContactAdapter
import thiengo.com.br.canalvinciusthiengo.adapter.ListItemAdapter
import thiengo.com.br.canalvinciusthiengo.domain.*


class BooksFragment : FrameworkListFragment() {

    companion object{
        const val KEY = "BooksFragment_key"
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated( savedInstanceState )

        setUiModel(
            titleText = getString( R.string.books_content_title )
        )

        val adapter = ListItemAdapter(
            context = activity!!,
            items = BooksData.getBooks(),
            callExternalAppCallback = {
                item -> callExternalApp(
                    webUri = item.getWebUri(),
                    failMessage = String.format(
                        getString( R.string.books_toast_alert ),
                        (item as Book).title
                    )
                )
            }
        )
        initList( adapter = adapter )

        /*val adapter = BookAdapter(
            context = activity!!,
            books = BooksData.getBooks(),
            callBookPageCallback = {
                book -> callExternalApp(
                    webUri = book.webPageUri(),
                    failMessage = String.format(
                        getString( R.string.books_toast_alert ),
                        book.title
                    )
                )
            }
        )
        initList( adapter = adapter )*/
    }
}