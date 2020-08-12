package thiengo.com.br.canalvinciusthiengo.model.parse.video

/**
 * Contém dados importantes ao app da thumb
 * do último vídeo liberado no canal YouTube
 * do aplicativo.
 *
 * O objetivo desta classe (objetos desta classe)
 * é ser parte do parse do JSON (via Gson API)
 * que é retornado pelo servidor do YouTube com
 * os dados de PlayLists do canal vinculado ao
 * app.
 *
 * Apesar de outros dados de thumb estarem
 * disponíveis no JSON, o que importa para o
 * aplicativo é somente a URL.
 *
 * Com a configuração atual de uso da API Gson
 * para aplicação do parse JSON, as propriedades de
 * classes parse devem ter os mesmos rótulos dos
 * campos referentes a elas em JSON.
 *
 * @property url URL da thumb do vídeo.
 * @constructor cria um objeto completo do tipo
 * ThumbParse.
 */
class ThumbParse( val url: String )