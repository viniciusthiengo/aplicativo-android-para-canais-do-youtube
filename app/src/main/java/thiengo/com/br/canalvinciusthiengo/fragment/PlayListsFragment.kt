package thiengo.com.br.canalvinciusthiengo.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_play_lists.*
import kotlinx.android.synthetic.main.no_data_message_block.*
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.adapter.ListItemAdapter
import thiengo.com.br.canalvinciusthiengo.adapter.PlayListAdapter
import thiengo.com.br.canalvinciusthiengo.data.UtilDatabase
import thiengo.com.br.canalvinciusthiengo.domain.Course
import thiengo.com.br.canalvinciusthiengo.domain.CoursesData
import thiengo.com.br.canalvinciusthiengo.domain.PlayList
import thiengo.com.br.canalvinciusthiengo.domain.PlayListData
import thiengo.com.br.canalvinciusthiengo.network.UtilNetwork


class PlayListsFragment : Fragment() {

    companion object{
        const val KEY = "PlayListsFragment_key"
    }
    private val playLists = PlayListData.getInitialPlayLists()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {

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
            uiDataStatus( status = UiFragDataStatus.LOADING )
            UtilDatabase
                .getInstance( context = activity!!.applicationContext )
                .getAllPlayLists{
                    setUiModel( pLists = it )
                }
        }
    }

    private fun setListeners(){
        srl_update_content.setOnRefreshListener {
            swipeRefreshStatus( status = true )
            retrieveData()
        }
    }

    private fun setUiModel( pLists: List<PlayList>? ){
        if( !pLists.isNullOrEmpty() ){

            activity?.runOnUiThread {
                uiDataStatus( status = UiFragDataStatus.LOADED )

                if (!pLists.equals( playLists )) {
                    playLists.clear()
                    playLists.addAll( pLists )
                }

                rv_play_lists
                    ?.adapter
                    ?.notifyDataSetChanged()
            }
        }
        else{
            uiDataStatus( status = UiFragDataStatus.NO_MAIN_CONTENT )
        }
    }

    private fun swipeRefreshStatus( status : Boolean ){
        activity?.runOnUiThread {
            srl_update_content?.isRefreshing = status
        }
    }

    private fun retrieveData(){
        UtilNetwork
            .getInstance( context = activity!! )
            .retrievePlayLists(
                callbackSuccess = {
                    swipeRefreshStatus( status = false )
                    setUiModel( it )
                },
                callbackFailure = {
                    swipeRefreshStatus( status = false )
                }
            )
    }

    private fun uiDataStatus( status: UiFragDataStatus ){
        activity?.runOnUiThread {
            var rvPlayLists = View.GONE
            var rlNoDataMessageContainer = View.VISIBLE
            var tvNoDataStatus = View.GONE
            swipeRefreshStatus( status = false )
            pb_load_content?.hide()

            when( status ){
                UiFragDataStatus.LOADING -> {
                    pb_load_content?.show()
                }
                UiFragDataStatus.NO_MAIN_CONTENT -> {
                    tv_no_data?.text = getString( R.string.no_playlists_yet )
                    tvNoDataStatus = View.VISIBLE
                }
                else -> {
                    rlNoDataMessageContainer = View.GONE
                    rvPlayLists = View.VISIBLE
                }
            }

            rv_play_lists?.visibility = rvPlayLists
            rl_no_data_message_container?.visibility = rlNoDataMessageContainer
            tv_no_data?.visibility = tvNoDataStatus
        }
    }

    private fun initPlayListList(){

        val layoutManager = LinearLayoutManager( activity )
        rv_play_lists?.layoutManager = layoutManager

        rv_play_lists?.setHasFixedSize( true )
        rv_play_lists?.adapter = PlayListAdapter(
            context = activity!!,
            playLists = playLists,
            callYouTubePlayListCallback = {
                callYouTubePlayListCallback(
                    playList = it
                )
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