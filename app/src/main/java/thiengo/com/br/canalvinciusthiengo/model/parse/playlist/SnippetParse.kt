package thiengo.com.br.canalvinciusthiengo.model.parse.playlist

/**
 * Contém o título da PlayList.
 *
 * O objetivo desta classe (objetos desta classe)
 * é apenas de ser parte do parse do JSON (via
 * Gson API) que é retornado pelo servidor do
 * YouTube com os dados de PlayLists do canal
 * vinculado ao app.
 *
 * Apesar de outros dados de snippet estarem
 * disponíveis no JSON, o que importa para o
 * aplicativo é somente o título (title).
 *
 * Com a configuração atual de uso da API Gson
 * para aplicação do parse JSON, as propriedades de
 * classes parses devem ter os mesmos rótulos dos
 * campos referentes a elas em JSON.
 *
 * @property title título da PlayList.
 * @constructor cria um objeto completo do tipo
 * SnippetParse.
 */
class SnippetParse( val title: String )