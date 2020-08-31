package thiengo.com.br.canalvinciusthiengo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import thiengo.com.br.canalvinciusthiengo.R

/**
 * Contém os dados sobre o canal. Dados em texto
 * e mais detalhados sobre a proposta do canal
 * YouTube do app.
 *
 * @constructor cria um objeto completo do tipo
 * [AboutChannelFragment].
 */
class AboutChannelFragment : Fragment() {

    companion object {
        /**
         * Constante com o identificador único do
         * fragmento [AboutChannelFragment] para que
         * ele seja encontrado na pilha de fragmentos
         * e assim não seja necessária a construção
         * de mais de um objeto deste fragmento em
         * memória enquanto o aplicativo estiver em
         * execução.
         */
        const val KEY = "AboutChannelFragment_key"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {

        return inflater.inflate(
            R.layout.fragment_about_channel,
            container,
            false
        )
    }
}