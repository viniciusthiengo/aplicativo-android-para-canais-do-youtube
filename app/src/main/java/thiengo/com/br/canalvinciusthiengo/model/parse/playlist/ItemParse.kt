package thiengo.com.br.canalvinciusthiengo.model.parse.playlist

/**
 * Contém todos os dados necessários, em app, de
 * uma PlayList.
 *
 * O objetivo desta classe (objetos desta classe)
 * é apenas de ser parte do parse do JSON (via
 * Gson API) que é retornado pelo servidor do
 * YouTube com os dados de PlayLists do canal
 * vinculado ao app.
 *
 * Apesar de outros dados de item estarem
 * disponíveis no JSON, o que importa para o
 * aplicativo é somente o id e o título.
 *
 * Com a configuração atual de uso da API Gson
 * para aplicação do parse JSON, as propriedades de
 * classes parses devem ter os mesmos rótulos dos
 * campos referentes a elas em JSON.
 *
 * @property id identificador único da PlayList.
 * @property snippet contém a informação de
 * título da PlayList.
 * @constructor cria um objeto do tipo
 * ItemParse.
 */
class ItemParse(
    val id: String,
    private val snippet: SnippetParse? ){

    /**
     * @property title contém o título da PlayList.
     * O método get() foi sobrescrito pois é possível,
     * mesmo que pouco provável, que snippet seja
     * null e assim não haja título de PlayList.
     * Porém devido ao algoritmo definido em get()
     * nunca é retornado null para a propriedade
     * title de ItemParse.
     * @return uma String válida de título de
     * PlayList.
     */
    val title: String
        get() : String {
            return if( snippet != null ){
                snippet.title
            }
            else{
                ""
            }
        }
}