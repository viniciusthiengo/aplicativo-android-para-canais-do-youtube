package thiengo.com.br.canalvinciusthiengo.model.parse.video

/**
 * Contém todos os dados necessários, em app, do
 * último vídeo liberado no canal YouTube do
 * aplicativo.
 *
 * O objetivo desta classe (objetos desta classe)
 * é apenas de ser parte do parse do JSON (via
 * Gson API) que é retornado pelo servidor do
 * YouTube com os dados do último vídeo liberado
 * do canal vinculado ao app.
 *
 * Apesar de outros dados estarem
 * disponíveis no JSON, o que importa para o
 * aplicativo é somente o id e alguns dados
 * contidos em snippet.
 *
 * Com a configuração atual de uso da API Gson
 * para aplicação do parse JSON, as propriedades de
 * classes parse devem ter os mesmos rótulos dos
 * campos referentes a elas em JSON.
 *
 * @property snippet contém informações de título,
 * descrição e thumb do vídeo.
 * @property id contém o identificador único do
 * vídeo.
 * @constructor cria um objeto completo do tipo
 * ItemParse.
 */
class ItemParse(
    val snippet: SnippetParse,
    val id: IdParse )