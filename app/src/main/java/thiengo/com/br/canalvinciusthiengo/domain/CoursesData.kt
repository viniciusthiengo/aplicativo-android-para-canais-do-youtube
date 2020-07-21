package thiengo.com.br.canalvinciusthiengo.domain

class CoursesData {

    companion object{

        fun getCourses()
            = listOf(
                Course(
                    title = "Android: Prototipagem Profissional de Aplicativos",
                    categories = listOf( "Android", "Prototipagem", "Lado estratégico" ),
                    amountVideos = 186,
                    webPage = "https://www.udemy.com/android-prototipagem-profissional-de-aplicativos/?persist_locale&locale=pt_BR"
                )
            )
    }
}