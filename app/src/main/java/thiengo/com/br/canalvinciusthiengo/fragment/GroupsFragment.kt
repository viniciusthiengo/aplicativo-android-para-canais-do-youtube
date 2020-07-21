package thiengo.com.br.canalvinciusthiengo.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_groups.*
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.adapter.GroupAdapter
import thiengo.com.br.canalvinciusthiengo.domain.Group
import thiengo.com.br.canalvinciusthiengo.domain.GroupsData


class GroupsFragment : Fragment() {

    companion object{
        const val KEY = "GroupsFragment_key"
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(
            R.layout.fragment_groups,
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
        rv_groups.layoutManager = layoutManager

        rv_groups.setHasFixedSize( true )
        rv_groups.adapter = GroupAdapter(
            context = activity!!,
            groups = GroupsData.getGroups(),
            callGroupAppCallback = {
                group -> callGroupAppCallback( group )
            }
        )
    }

    private fun callGroupAppCallback( group: Group ){

        val intent = Intent(
            Intent.ACTION_VIEW,
            group.appUri()
        )

        /*
         * Pode ocorrer de não haver o aplicativo correto
         * no aparelho do usuário para abrir o grupo
         * acionado em lista.
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
                        getString( R.string.groups_toast_alert ),
                        group.place,
                        group.name
                    ),
                    Toast.LENGTH_LONG
                )
                .show()

            return
        }

        activity!!.startActivity( intent )
    }
}