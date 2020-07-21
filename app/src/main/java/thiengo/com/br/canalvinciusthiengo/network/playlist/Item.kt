package thiengo.com.br.canalvinciusthiengo.network.playlist

class Item(
    val id: String,
    private val snippet: Snippet?
) {

    val title: String
        get() : String{
            return if( snippet != null ){
                snippet.title
            }
            else{
                ""
            }
        }
}