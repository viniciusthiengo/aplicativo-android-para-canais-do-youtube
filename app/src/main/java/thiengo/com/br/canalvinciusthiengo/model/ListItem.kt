package thiengo.com.br.canalvinciusthiengo.model

import android.content.res.Resources
import android.net.Uri

/**
 * Interface utilizada como "estrutura comum" a todos
 * os itens de lista presentes em projeto.
 *
 * Assim é possível economizar na quantidade de código
 * que seria necessária para inúmeros Adapters e
 * ViewHolders de cada Fragment que tem um RecyclerView
 * (framework de lista).
 */
interface ListItem {

    /**
     * Retorna o principal texto em item de lista, o
     * que tem destaque e maior tamanho de fonte.
     *
     * @return o texto principal do item de lista.
     */
    fun getMainText() : String

    /**
     * Retorna o possível primeiro texto auxiliar do
     * item de lista. Texto com informações extras,
     * porém ainda necessárias.
     *
     * @return o texto primário auxiliar do item de
     * lista.
     */
    fun getFirstAuxText() : String
        = ""

    /**
     * Retorna o possível segundo texto auxiliar do
     * item de lista. Texto com informações extras,
     * porém ainda necessárias.
     *
     * @param resources objeto para acesso
     * a recursos do sistema.
     * @return o texto secundário auxiliar do
     * item de lista.
     */
    fun getSecondAuxText( resource: Resources ) : String
        = ""

    /**
     * Retorna a Web URI que deve ser acionada
     * junto a um objeto Intent em uma invocação
     * de startActivity().
     *
     * Isso para abrir o aplicativo responsável
     * pelo conteúdo completo informado em item de
     * lista.
     *
     * @return a Web URI do item acionado.
     */
    fun getWebUri() : Uri?
        = null

    /**
     * Retorna a app URI que deve ser acionada
     * junto a um objeto Intent em uma invocação
     * de startActivity().
     *
     * Isso para abrir o aplicativo responsável
     * pelo conteúdo completo informado em item de
     * lista.
     *
     * @return a app URI do item acionado.
     */
    fun getAppUri() : Uri?
        = null

    /**
     * Retorna o identificador único do ícone que
     * melhor representa o item de lista.
     *
     * O ícone deve estar local no app, como recurso
     * do aplicativo (/res/drawable).
     *
     * @return o identificador único do ícone.
     */
    fun getIcon() : Int
}