package thiengo.com.br.canalvinciusthiengo.ui.fragment

import android.os.Bundle
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.data.fixed.BooksData
import thiengo.com.br.canalvinciusthiengo.model.Book
import thiengo.com.br.canalvinciusthiengo.ui.adapter.ListItemAdapter

/**
 * Contém a lista de livros vinculados ou apenas
 * indicados pelo canal YouTube do app.
 *
 * @constructor cria um objeto completo do tipo
 * [BooksFragment].
 */
class BooksFragment : FrameworkListFragment() {

    companion object {
        /**
         * Constante com o identificador único do
         * fragmento [BooksFragment] para que
         * ele seja encontrado na pilha de fragmentos
         * e assim não seja necessária a construção
         * de mais de um objeto deste fragmento em
         * memória enquanto o aplicativo estiver em
         * execução.
         */
        const val KEY = "BooksFragment_key"
    }

    override fun onActivityCreated(
        savedInstanceState: Bundle? ){
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
    }
}