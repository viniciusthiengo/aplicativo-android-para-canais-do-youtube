package thiengo.com.br.canalvinciusthiengo.model.parse.video

/**
 * Contém o identificador único do vídeo.
 *
 * O objetivo desta classe (objetos desta classe)
 * é ser parte do parse do JSON (via Gson API) que
 * é retornado pelo servidor do YouTube com os
 * dados do último vídeo liberado no canal
 * vinculado do app.
 *
 * Com a configuração atual de uso da API Gson
 * para aplicação do parse JSON, as propriedades de
 * classes parses devem ter os mesmos rótulos dos
 * campos referentes a elas em JSON.
 *
 * @property videoId identificador único do vídeo.
 * @constructor cria um objeto completo do tipo
 * IdParse.
 */
class IdParse( val videoId: String )