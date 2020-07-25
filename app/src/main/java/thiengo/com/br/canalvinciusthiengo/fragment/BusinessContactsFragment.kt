package thiengo.com.br.canalvinciusthiengo.fragment

import android.os.Bundle
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.adapter.BusinessContactAdapter
import thiengo.com.br.canalvinciusthiengo.adapter.ListItemAdapter
import thiengo.com.br.canalvinciusthiengo.domain.BusinessContact
import thiengo.com.br.canalvinciusthiengo.domain.BusinessContactData
import thiengo.com.br.canalvinciusthiengo.domain.Course
import thiengo.com.br.canalvinciusthiengo.domain.CoursesData


class BusinessContactsFragment : FrameworkListFragment() {

    companion object{
        const val KEY = "BusinessContactsFragment_key"
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated( savedInstanceState )

        setUiModel(
            titleText = getString( R.string.business_contacts_content_title ),
            subTitleText = getString( R.string.business_contacts_desc )
        )

        val adapter = ListItemAdapter(
            context = activity!!,
            items = BusinessContactData.getBusinessContacts(),
            callExternalAppCallback = {
                item -> callExternalApp(
                    appUri = item.getAppUri(),
                    failMessage = String.format(
                        getString( R.string.business_contact_toast_alert ),
                        (item as BusinessContact).place,
                        item.contact
                    )
                )
            }
        )
        initList( adapter = adapter )

        /*val adapter = BusinessContactAdapter(
            context = activity!!,
            contacts = BusinessContactData.getBusinessContacts(),
            callBusinessAppCallback = {
                contact -> callExternalApp(
                    appUri = contact.appUri(),
                    failMessage = String.format(
                        getString( R.string.business_contact_toast_alert ),
                        contact.place,
                        contact.contact
                    )
                )
            }
        )
        initList( adapter = adapter )*/
    }
}