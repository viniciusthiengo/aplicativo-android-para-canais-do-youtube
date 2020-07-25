package thiengo.com.br.canalvinciusthiengo.network.playlistparse

class ItemParse(
    val id: String,
    private val snippet: SnippetParse? ){

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