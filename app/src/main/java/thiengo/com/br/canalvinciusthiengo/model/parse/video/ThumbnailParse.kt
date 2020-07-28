package thiengo.com.br.canalvinciusthiengo.model.parse.video

/**
 * Contém dados da versão de mais alta resolução
 * da thumb do último vídeo liberado no canal
 * YouTube do aplicativo.
 *
 * O objetivo desta classe (objetos desta classe)
 * é ser parte do parse do JSON (via Gson API)
 * que é retornado pelo servidor do YouTube com
 * os dados de PlayLists do canal vinculado ao
 * app.
 *
 * Apesar de outros dados de thumbnail estarem
 * disponíveis no JSON, o que importa para o
 * aplicativo é somente os dados de versão de
 * alta resolução da thumb.
 *
 * Com a configuração atual de uso da API Gson
 * para aplicação do parse JSON, as propriedades de
 * classes parses devem ter os mesmos rótulos dos
 * campos referentes a elas em JSON.
 *
 * @property high thumb de mais alta resolução
 * do vídeo.
 * @constructor cria um objeto completo do tipo
 * ThumbnailParse.
 */
class ThumbnailParse( val high: ThumbParse )