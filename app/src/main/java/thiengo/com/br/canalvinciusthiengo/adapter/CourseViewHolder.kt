package thiengo.com.br.canalvinciusthiengo.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.domain.Course


class CourseViewHolder(
    val adapter: CourseAdapter,
    val callCoursePageCallback: (Course)->Unit,
    itemView: View ) : RecyclerView.ViewHolder( itemView ), View.OnClickListener {

    var tvTitle: TextView
    var tvCategories: TextView
    var tvAmountVideos: TextView

    init {
        itemView.setOnClickListener( this )

        tvTitle = itemView.findViewById( R.id.tv_title )
        tvCategories = itemView.findViewById( R.id.tv_categories )
        tvAmountVideos = itemView.findViewById( R.id.tv_amount_videos )
    }

    fun setModel( course: Course ) {

        tvTitle.text = course.title
        tvCategories.text = course.categoriesAsItemLabel()
        tvAmountVideos.text = course.amountVideosAsItemLabel(
            adapter.context.resources
        )
    }

    override fun onClick( view: View ) {

        callCoursePageCallback(
            adapter.courses[ adapterPosition ]
        )
    }
}