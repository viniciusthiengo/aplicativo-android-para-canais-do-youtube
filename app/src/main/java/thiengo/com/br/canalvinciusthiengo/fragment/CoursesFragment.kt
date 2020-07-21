package thiengo.com.br.canalvinciusthiengo.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_courses.*
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.adapter.CourseAdapter
import thiengo.com.br.canalvinciusthiengo.domain.Course
import thiengo.com.br.canalvinciusthiengo.domain.CoursesData


class CoursesFragment : Fragment() {

    companion object{
        const val KEY = "CoursesFragment_key"
    }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

        return inflater.inflate(
            R.layout.fragment_courses,
            container,
            false
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated( savedInstanceState )

        initCoursesList()
    }

    private fun initCoursesList(){

        val layoutManager = LinearLayoutManager( activity )
        rv_courses.layoutManager = layoutManager

        rv_courses.setHasFixedSize( true )
        rv_courses.adapter = CourseAdapter(
            context = activity!!,
            courses = CoursesData.getCourses(),
            callCoursePageCallback = {
                book -> callCoursePageCallback( book )
            }
        )
    }

    private fun callCoursePageCallback( course: Course ){

        val intent = Intent(
            Intent.ACTION_VIEW,
            course.webPageUri()
        )

        /*
         * É utópico, mas pode ocorrer de não haver um
         * navegador Web no aparelho do usuário que abra a URL do
         * curso acionado.
         *
         * Sendo assim, ao invés de gerar uma exceção, nós
         * avisamos ao usuário a necessidade de instalar o
         * aplicativo adequado.
         * */
        if( intent.resolveActivity( activity!!.packageManager ) == null ){
            Toast
                .makeText(
                    activity,
                    String.format(
                        getString( R.string.course_toast_alert ),
                        course.title
                    ),
                    Toast.LENGTH_LONG
                )
                .show()

            return
        }

        activity!!.startActivity( intent )
    }
}