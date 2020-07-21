package thiengo.com.br.canalvinciusthiengo.network.video

data class Video( private val items: List<Item> ) {

    val id: String
        get() : String{
            return if( !items.isNullOrEmpty() ){
                items[0].id.videoId
            }
            else{
                ""
            }
        }

    val title: String
        get() : String{
            return if( !items.isNullOrEmpty() ){
                items[0].snippet.title
            }
            else{
                ""
            }
        }

    val description: String
        get() : String{
            return if( !items.isNullOrEmpty() ){
                items[0].snippet.description
            }
            else{
                ""
            }
        }

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