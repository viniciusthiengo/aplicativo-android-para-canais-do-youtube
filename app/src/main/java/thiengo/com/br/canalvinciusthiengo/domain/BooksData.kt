package thiengo.com.br.canalvinciusthiengo.domain

class BooksData {

    companion object{

        fun getBooks()
            = listOf(
                Book(
                    title = "Desenvolvedor Kotlin Android - Bibliotecas para o dia a dia",
                    categories = listOf( "Android", "Kotlin" ),
                    webPage = "https://www.thiengo.com.br/livro-desenvolvedor-kotlin-android"
                ),
                Book(
                    title = "Refatorando Para Programas Limpos",
                    categories = listOf( "Engenharia de Software", "Código limpo" ),
                    webPage = "https://www.thiengo.com.br/livro-refatorando-para-programas-limpos"
                ),
                Book(
                    title = "Receitas Para Desenvolvedores",
                    categories = listOf( "Android", "Java" ),
                    webPage = "https://www.thiengo.com.br/livro-receitas-para-desenvolvedores-android"
                ),
                Book(
                    title = "Porque e Como Utilizar Vetores no Android",
                    categories = listOf( "Android", "Kotlin", "Drawable" ),
                    webPage = "https://drive.google.com/file/d/1XNt1gmfcVeqDQFhqR7MZEf8dSurzzdyl/view?usp=sharing"
                )
            )
    }
}