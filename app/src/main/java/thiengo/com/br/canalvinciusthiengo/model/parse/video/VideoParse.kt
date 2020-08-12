package thiengo.com.br.canalvinciusthiengo.model.parse.video

/**
 * Contém todos os dados necessários em aplicativo
 * do último vídeo liberado no canal YouTube do app.
 *
 * O objetivo desta classe (objetos desta classe)
 * é ser parte do parse do JSON (via Gson API)
 * que é retornado pelo servidor do YouTube com
 * os dados do último vídeo liberado do canal
 * vinculado ao app.
 *
 * Apesar de outros dados estarem disponíveis no
 * JSON, os que importam para o aplicativo são:
 * o id, o título, a descrição e a thumb.
 *
 * Com a configuração atual de uso da API Gson
 * para aplicação do parse JSON, as propriedades de
 * classes parse devem ter os mesmos rótulos dos
 * campos referentes a elas em JSON.
 *
 * @property items lista que contém somente o
 * último vídeo liberado no canal.
 * @constructor cria um objeto completo do tipo
 * VideoParse.
 */
data class VideoParse(
    private val items: List<ItemParse> ){

    /**
     * @property id contém o identificador único do
     * vídeo. O método get() foi sobrescrito pois é
     * possível, mesmo que pouco provável, que items
     * seja null ou esteja vazio e assim não haja
     * dados de último vídeo. Porém devido ao
     * algoritmo definido em get() nunca é retornado
     * null para a propriedade id de VideoParse.
     * @return o identificador único do vídeo.
     */
    val id: String
        get() : String{
            return if( !items.isNullOrEmpty() ){
                items[0].id.videoId
            }
            else{
                ""
            }
        }

    /**
     * @property title contém o título do vídeo. O
     * método get() foi sobrescrito pois é possível
     * que items seja null ou esteja vazio e assim
     * não haja dados de último vídeo. Porém devido
     * ao algoritmo definido em get() nunca é
     * retornado null para a propriedade title de
     * VideoParse.
     * @return o título do vídeo.
     */
    val title: String
        get() : String{
            return if( !items.isNullOrEmpty() ){
                items[0].snippet.title
            }
            else{
                ""
            }
        }

    /**
     * @property description contém a descrição do
     * vídeo. O método get() foi sobrescrito pois
     * é possível que items seja null ou esteja
     * vazio e assim não haja dados de último
     * vídeo. Porém devido ao algoritmo definido
     * em get() nunca é retornado null para a
     * propriedade description de VideoParse.
     * @return a descrição do vídeo.
     */
    val description: String
        get() : String{
            return if( !items.isNullOrEmpty() ){
                items[0].snippet.description
            }
            else{
                ""
            }
        }

    /**
     * @property thumbUrl contém a URL da thumb do
     * vídeo. O método get() foi sobrescrito pois
     * é possível que items seja null ou esteja
     * vazio e assim não haja dados de último
     * vídeo. Porém devido ao algoritmo definido
     * em get() nunca é retornado null para a
     * propriedade thumbUrl de VideoParse.
     * @return a URl da thumb de alta resolução
     * do vídeo.
     */
    val thumbUrl: String
        get() : String{
            return if( !items.isNullOrEmpty() ){
                items[0].snippet.thumbnails.high.url
            }
            else{
                ""
            }
        }
}