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
import thiengo.com.br.canalvinciusthiengo.adapter.GroupAdapter
import thiengo.com.br.canalvinciusthiengo.adapter.ListItemAdapter
import thiengo.com.br.canalvinciusthiengo.domain.*


class GroupsFragment : FrameworkListFragment() {

    companion object{
        const val KEY = "GroupsFragment_key"
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated( savedInstanceState )

        setUiModel(
            titleText = getString( R.string.groups_content_title ),
            subTitleText = getString( R.string.groups_desc )
        )

        val adapter = ListItemAdapter(
            context = activity!!,
            items = GroupsData.getGroups(),
            callExternalAppCallback = {
                item -> callExternalApp(
                    appUri = item.getAppUri(),
                    failMessage = String.format(
                        getString( R.string.groups_toast_alert ),
                        (item as Group).place,
                        item.name
                    )
                )
            }
        )
        initList( adapter = adapter )

        /*val adapter = GroupAdapter(
            context = activity!!,
            groups = GroupsData.getGroups(),
            callGroupAppCallback = {
                group -> callExternalApp(
                    appUri = group.appUri(),
                    failMessage = String.format(
                        getString( R.string.groups_toast_alert ),
                        group.place,
                        group.name
                    )
                )
            }
        )
        initList( adapter = adapter )*/
    }
}