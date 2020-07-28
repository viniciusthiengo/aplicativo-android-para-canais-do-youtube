package thiengo.com.br.canalvinciusthiengo.model.parse.video

/**
 * Contém dados importantes ao app do último vídeo
 * liberado no canal YouTube do aplicativo.
 *
 * O objetivo desta classe (objetos desta classe)
 * é ser parte do parse do JSON (via Gson API)
 * que é retornado pelo servidor do YouTube com
 * os dados do último vídeo liberado no canal
 * vinculado do app.
 *
 * Apesar de outros dados de snippet estarem
 * disponíveis no JSON, o que importa para o
 * aplicativo são: título, descrição e thumbs.
 *
 * Com a configuração atual de uso da API Gson
 * para aplicação do parse JSON, as propriedades de
 * classes parses devem ter os mesmos rótulos dos
 * campos referentes a elas em JSON.
 *
 * @property title título do vídeo.
 * @property description descrição do vídeo.
 * @property thumbnails contém todas as versões
 * disponíveis de thumb do vídeo.
 * @constructor cria um objeto completo do tipo
 * SnippetParse.
 */
class SnippetParse(
    val title: String,
    val description: String,
    val thumbnails: ThumbnailParse )