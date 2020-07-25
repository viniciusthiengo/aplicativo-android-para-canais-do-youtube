package thiengo.com.br.canalvinciusthiengo.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_framework_list.*
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.adapter.BusinessContactAdapter
import thiengo.com.br.canalvinciusthiengo.adapter.CourseAdapter
import thiengo.com.br.canalvinciusthiengo.adapter.ListItemAdapter
import thiengo.com.br.canalvinciusthiengo.domain.BusinessContactData
import thiengo.com.br.canalvinciusthiengo.domain.Course
import thiengo.com.br.canalvinciusthiengo.domain.CoursesData


class CoursesFragment : FrameworkListFragment() {

    companion object{
        const val KEY = "CoursesFragment_key"
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated( savedInstanceState )

        setUiModel(
            titleText = getString( R.string.courses_content_title )
        )

        val adapter = ListItemAdapter(
            context = activity!!,
            items = CoursesData.getCourses(),
            callExternalAppCallback = {
                item -> callExternalApp(
                    webUri = item.getWebUri(),
                    appUri = item.getAppUri(),
                    failMessage = String.format(
                        getString( R.string.course_toast_alert ),
                        (item as Course).title
                    )
                )
            }
        )
        initList( adapter = adapter )

        /*val adapter = CourseAdapter(
            context = activity!!,
            courses = CoursesData.getCourses(),
            callCoursePageCallback = {
                course -> callExternalApp(
                    webUri = course.webPageUri(),
                    failMessage = String.format(
                        getString( R.string.course_toast_alert ),
                        course.title
                    )
                )
            }
        )
        initList( adapter = adapter )*/
    }
}