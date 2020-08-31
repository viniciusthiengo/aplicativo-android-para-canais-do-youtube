package thiengo.com.br.canalvinciusthiengo.data.fixed

import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.model.Book

/**
 * Contém os dados dos livros proprietários ou
 * apenas indicados pelo canal YouTube vinculado
 * ao aplicativo.
 *
 * O objetivo desta classe é trabalhar como uma
 * persistência local estática, fixa, que contém
 * os dados dos livros.
 *
 * Como esses dados tendem a sofrer poucas
 * alterações (incluindo a inserção de novos livros)
 * e com espaços de tempo longos entre as
 * alterações, então a melhor escolha foi o trabalho
 * deles em uma classe estática (companion object)
 * que trabalha como se fosse uma persistência de
 * dados estáticos.
 */
abstract class BooksData {

    companion object {
        /**
         * Retorna todos os livros (digitais ou não)
         * vinculados ao canal.
         *
         * @return lista não mutável de objetos
         * [Book].
         */
        fun getBooks()
            = listOf(
                Book(
                    title = "Desenvolvedor Kotlin Android - Bibliotecas para o dia a dia",
                    categories = listOf("Android", "Kotlin"),
                    webPage = "https://www.thiengo.com.br/livro-desenvolvedor-kotlin-android",
                    cover = R.drawable.ic_book_cover_red
                ),
                Book(
                    title = "Refatorando Para Programas Limpos",
                    categories = listOf("Engenharia de Software", "Código limpo"),
                    webPage = "https://www.thiengo.com.br/livro-refatorando-para-programas-limpos",
                    cover = R.drawable.ic_book_cover_yellow
                ),
                Book(
                    title = "Receitas Para Desenvolvedores",
                    categories = listOf("Android", "Java"),
                    webPage = "https://www.thiengo.com.br/livro-receitas-para-desenvolvedores-android",
                    cover = R.drawable.ic_book_cover_grey
                ),
                Book(
                    title = "Porque e Como Utilizar Vetores no Android",
                    categories = listOf("Android", "Kotlin", "Drawable"),
                    webPage = "https://www.thiengo.com.br/porque-e-como-utilizar-vetores-no-android",
                    cover = R.drawable.ic_book_cover_orange
                ),
                Book(
                    title = "Construa Um Aplicativo Android Completo Para YouTubers",
                    categories = listOf("Android", "Kotlin", "YouTuber"),
                    webPage = "https://www.thiengo.com.br/construa-um-aplicativo-android-completo-para-youtubers-parte-1",
                    cover = R.drawable.ic_book_cover_orange
                )
            )
    }
}