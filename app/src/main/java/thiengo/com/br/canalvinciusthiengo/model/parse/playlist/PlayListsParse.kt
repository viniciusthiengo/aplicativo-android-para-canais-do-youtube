package thiengo.com.br.canalvinciusthiengo.model.parse.playlist

/**
 * Contém os dados necessários em aplicativo de
 * todas as PlayLists retornadas do servidor de
 * dados do YouTube.
 *
 * O objetivo desta classe (objetos desta classe)
 * é ser parte do parse do JSON (via Gson API)
 * que é retornado pelo servidor do YouTube com
 * os dados de PlayLists do canal vinculado ao
 * app.
 *
 * Apesar de outros dados de PlayLists estarem
 * disponíveis no JSON, o que importa para o
 * aplicativo é somente o id e o título de cada
 * PlayList dentro da lista items.
 *
 * Com a configuração atual de uso da API Gson
 * para aplicação do parse JSON, as propriedades de
 * classes parse devem ter os mesmos rótulos dos
 * campos referentes a elas em JSON.
 *
 * @property items PlayLists retornadas pelo
 * YouTube Data API.
 * @constructor cria um objeto completo do tipo
 * PlayListsParse.
 */
class PlayListsParse( val items: List<ItemParse> )