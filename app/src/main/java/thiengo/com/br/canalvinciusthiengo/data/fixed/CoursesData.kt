package thiengo.com.br.canalvinciusthiengo.data.fixed

import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.model.Course

/**
 * Contém os dados dos cursos proprietários ou
 * apenas indicados pelo canal YouTube vinculado
 * ao aplicativo.
 *
 * O objetivo desta classe é trabalhar como uma
 * persistência local estática, fixa, que contém
 * os dados dos cursos.
 *
 * Como esses dados tendem a sofrer poucas
 * alterações (incluindo a inserção de novos cursos)
 * e com espaços de tempo longos entre as
 * alterações, então a melhor escolha foi o trabalho
 * deles em uma classe estática (companion object)
 * que trabalha como se fosse uma persistência de
 * dados estáticos.
 */
abstract class CoursesData {

    companion object{
        /**
         * Retorna todos os cursos (digitais ou não)
         * vinculados ao canal.
         *
         * @return lista não mutável de objetos
         * [Course].
         */
        fun getCourses()
            = listOf(
                Course(
                    title = "Android: Prototipagem Profissional de Aplicativos",
                    categories = listOf("Android", "Prototipagem", "Lado estratégico"),
                    amountVideos = 186,
                    webPage = "https://www.udemy.com/android-prototipagem-profissional-de-aplicativos/?persist_locale&locale=pt_BR",
                    cover = R.drawable.ic_courses_color
                )
            )
    }
}