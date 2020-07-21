package thiengo.com.br.canalvinciusthiengo.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_business_contacts.*
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.adapter.BusinessContactAdapter
import thiengo.com.br.canalvinciusthiengo.domain.BusinessContact
import thiengo.com.br.canalvinciusthiengo.domain.BusinessContactData


class BusinessContactsFragment : Fragment() {

    companion object{
        const val KEY = "BusinessContactsFragment_key"
    }


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

        return inflater.inflate(
            R.layout.fragment_business_contacts,
            container,
            false
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated( savedInstanceState )

        initNetworkList()
    }

    private fun initNetworkList(){

        val layoutManager = LinearLayoutManager( activity )
        rv_contacts.layoutManager = layoutManager

        rv_contacts.setHasFixedSize( true )
        rv_contacts.adapter = BusinessContactAdapter(
            context = activity!!,
            contacts = BusinessContactData.getBusinessContacts(),
            callBusinessAppCallback = {
                contact -> callBusinessAppCallback( contact )
            }
        )
    }

    private fun callBusinessAppCallback( contact: BusinessContact ){

        val intent = Intent(
            Intent.ACTION_VIEW,
            contact.appUri()
        )

        /*
         * Pode ocorrer de não haver o aplicativo correto
         * no aparelho do usuário. App que abra a URI da
         * rede de contato acionada.
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
                        getString( R.string.business_contact_toast_alert ),
                        contact.place,
                        contact.contact
                    ),
                    Toast.LENGTH_LONG
                )
                .show()

            return
        }

        activity!!.startActivity( intent )
    }
}