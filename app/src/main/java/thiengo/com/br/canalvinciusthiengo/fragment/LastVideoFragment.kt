package thiengo.com.br.canalvinciusthiengo.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_last_video.*
import kotlinx.android.synthetic.main.no_data_message_block.*
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.data.UtilDatabase
import thiengo.com.br.canalvinciusthiengo.domain.LastVideo
import thiengo.com.br.canalvinciusthiengo.network.NetworkRetrieveDataProblem
import thiengo.com.br.canalvinciusthiengo.network.UtilNetwork


class LastVideoFragment : Fragment() {

    companion object{
        const val KEY = "LastVideoFragment_key"
    }
    private var lastVideo: LastVideo? = null


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

        return inflater.inflate(
            R.layout.fragment_last_video,
            container,
            false
        )
    }

    override fun onActivityCreated( savedInstanceState: Bundle? ) {
        super.onActivityCreated( savedInstanceState )

        setListeners()

        if( lastVideo != null ){
            setUiModel( video = lastVideo )
        }
        else{
            uiDataStatus( status = UiDataStatus.LOADING )
            UtilDatabase
                .getInstance( context = activity!!.applicationContext )
                .getLastVideo{
                    activity!!.runOnUiThread {
                        setUiModel( video = it )
                    }
                }
        }
    }

    private fun setListeners(){
        srl_update_content.setOnRefreshListener {
            srl_update_content.isRefreshing = true
            retrieveData( lVideo = lastVideo )
        }
        cv_last_video.setOnClickListener{
            openVideoOnYouTube()
        }
    }

    private fun setUiModel( video: LastVideo? ){
        if( video != null ){
            uiDataStatus( status = UiDataStatus.LOADED )
            lastVideo = video

            tv_last_video_title.text = video.title
            tv_last_video_desc.text = video.description
            Picasso
                .get()
                .load( video.thumbUrl )
                .into( iv_last_video_thumb )
        }
        else{
            retrieveData()
        }
    }

    private fun retrieveData( lVideo: LastVideo? = null ){
        /*
         * Se lVideo == null então o método retrieveData()
         * não está sendo invocado devido ao acionamento
         * de SwipeRefresh.
         *
         * Sendo assim o layout tem que ficar no status
         * UiDataStatus.LOADING.
         * */
        if( lVideo == null ){
            uiDataStatus( status = UiDataStatus.LOADING )
        }

        UtilNetwork
            .getInstance( context = activity!! )
            .retrieveLastVideo(
                callbackSuccess = {
                    setUiModel( it )
                },
                callbackFailure = {
                    if( lVideo == null ){
                        if( it == NetworkRetrieveDataProblem.NO_VIDEO ){
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
            var cvLastVideoStatus = View.GONE
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
                    tv_no_data.text = getString( R.string.no_video_yet )
                    tvNoDataStatus = View.VISIBLE
                }
                UiDataStatus.NO_CONNECTION -> {
                    tvNoInternetConnection = View.VISIBLE
                }
                else -> {
                    rlNoDataMessageContainer = View.GONE
                    cvLastVideoStatus = View.VISIBLE
                }
            }

            cv_last_video.visibility = cvLastVideoStatus
            rl_no_data_message_container.visibility = rlNoDataMessageContainer
            tv_no_data.visibility = tvNoDataStatus
            tv_no_internet_connection.visibility = tvNoInternetConnection
        }
        catch( e: Exception ){}
    }

    private fun openVideoOnYouTube(){
        val intent = Intent(
            Intent.ACTION_VIEW,
            lastVideo!!.appUri()
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
                        getString( R.string.last_video_toast_alert ),
                        lastVideo!!.title
                    ),
                    Toast.LENGTH_LONG
                )
                .show()

            return
        }

        activity!!.startActivity( intent )
    }
}