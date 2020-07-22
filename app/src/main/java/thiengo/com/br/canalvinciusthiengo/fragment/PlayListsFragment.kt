package thiengo.com.br.canalvinciusthiengo.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_play_lists.*
import kotlinx.android.synthetic.main.no_data_message_block.*
import thiengo.com.br.canalvinciusthiengo.MainActivity
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.adapter.PlayListAdapter
import thiengo.com.br.canalvinciusthiengo.data.UtilDatabase
import thiengo.com.br.canalvinciusthiengo.domain.PlayList
import thiengo.com.br.canalvinciusthiengo.network.NetworkRetrieveDataProblem
import thiengo.com.br.canalvinciusthiengo.network.UtilNetwork


class PlayListsFragment : Fragment() {

    companion object{
        const val KEY = "PlayListsFragment_key"
    }
    private val playLists : MutableList<PlayList> = mutableListOf()


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

        return inflater.inflate(
            R.layout.fragment_play_lists,
            container,
            false
        )
    }

    override fun onActivityCreated( savedInstanceState: Bundle? ){
        super.onActivityCreated( savedInstanceState )

        setListeners()
        initPlayListList()

        if( playLists.isNotEmpty() ){
            setUiModel( pLists = playLists )
        }
        else{
            uiDataStatus( status = UiDataStatus.LOADING )
            UtilDatabase
                .getInstance( context = activity!!.applicationContext )
                .getAllPlayLists{
                    activity!!.runOnUiThread {
                        setUiModel( pLists = it )
                    }
                }
        }
    }

    private fun setListeners(){
        srl_update_content.setOnRefreshListener {
            srl_update_content.isRefreshing = true
            retrieveData( pLists = playLists )
        }
    }

    private fun setUiModel( pLists: List<PlayList>? ){
        try{
            if( !pLists.isNullOrEmpty() ){

                uiDataStatus( status = UiDataStatus.LOADED )

                if( !pLists.equals( playLists ) ){
                    Log.i(MainActivity.LOG_TAG, "setUiModel() INSIDE")
                    playLists.clear()
                    playLists.addAll( pLists )
                }

                rv_play_lists
                    .adapter!!
                    .notifyDataSetChanged()
            }
            else{
                retrieveData()
            }
        }
        catch( e: Exception ){}
    }

    private fun retrieveData( pLists: List<PlayList>? = null ){
        /*
         * Se pLists.isNullOrEmpty() for true então o método
         *  retrieveData() não está sendo invocado devido ao
         * acionamento de SwipeRefresh.
         *
         * Sendo assim o layout tem que ficar no status
         * UiDataStatus.LOADING.
         * */
        if( pLists.isNullOrEmpty() ){
            uiDataStatus( status = UiDataStatus.LOADING )
        }

        UtilNetwork
            .getInstance( context = activity!! )
            .retrievePlayLists(
                callbackSuccess = {
                    setUiModel( it )
                },
                callbackFailure = {
                    if( pLists.isNullOrEmpty() ){
                        if( it == NetworkRetrieveDataProblem.NO_PLAYLISTS ){
                            uiDataStatus( status = UiDataStatus.NO_MAIN_CONTENT )
                        }
                        else{
                            uiDataStatus( status = UiDataStatus.NO_CONNECTION )
                        }
                    }
                    else{
                        uiDataStatus( status = UiDataStatus.LOADED )
                    }
                }
            )
    }

    private fun uiDataStatus( status: UiDataStatus ){
        try{
            var rvPlayLists = View.GONE
            var rlNoDataMessageContainer = View.VISIBLE
            var tvNoDataStatus = View.GONE
            var tvNoInternetConnection = View.GONE
            srl_update_content.isRefreshing = false
            pb_load_content.hide()

            when( status ){
                UiDataStatus.LOADING -> {
                    pb_load_content.show()
                }
                UiDataStatus.NO_MAIN_CONTENT -> {
                    tv_no_data.text = getString( R.string.no_playlists_yet )
                    tvNoDataStatus = View.VISIBLE
                }
                UiDataStatus.NO_CONNECTION -> {
                    tvNoInternetConnection = View.VISIBLE
                }
                else -> {
                    rlNoDataMessageContainer = View.GONE
                    rvPlayLists = View.VISIBLE
                }
            }

            rv_play_lists.visibility = rvPlayLists
            rl_no_data_message_container.visibility = rlNoDataMessageContainer
            tv_no_data.visibility = tvNoDataStatus
            tv_no_internet_connection.visibility = tvNoInternetConnection
        }
        catch( e: Exception ){}
    }

    private fun initPlayListList(){

        val layoutManager = LinearLayoutManager( activity )
        rv_play_lists.layoutManager = layoutManager

        rv_play_lists.setHasFixedSize( true )
        rv_play_lists.adapter = PlayListAdapter(
            context = activity!!,
            playLists = playLists,
            callYouTubePlayListCallback = {
                playList -> callYouTubePlayListCallback( playList )
            }
        )
    }

    private fun callYouTubePlayListCallback( playList: PlayList ){

        val intent = Intent(
            Intent.ACTION_VIEW,
            playList.appUri()
        )

        /*
         * É utópico, mas pode ocorrer de não haver instalado
         * no aparelho do usuário o aplicativo do YouTube e
         * nem mesmo um navegador Web.
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
                        getString( R.string.playlist_toast_alert ),
                        playList.title
                    ),
                    Toast.LENGTH_LONG
                )
                .show()

            return
        }

        activity!!.startActivity( intent )
    }
}