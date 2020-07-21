package thiengo.com.br.canalvinciusthiengo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.domain.Course

class CourseAdapter(
    val context: Context,
    val courses: List<Course>,
    val callCoursePageCallback: (Course)->Unit )
    : RecyclerView.Adapter<CourseViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int ) : CourseViewHolder {

        val layout = LayoutInflater
            .from( context )
            .inflate(
                R.layout.course_item,
                parent,
                false
            )

        return CourseViewHolder(
            adapter = this,
            callCoursePageCallback = callCoursePageCallback,
            itemView = layout
        )
    }

    override fun onBindViewHolder(
        holder: CourseViewHolder,
        position: Int ){

        holder.setModel(
            course = courses[ position ]
        )
    }

    override fun getItemCount() = courses.size
}