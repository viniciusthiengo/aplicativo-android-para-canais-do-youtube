package thiengo.com.br.canalvinciusthiengo.data.dynamic

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import thiengo.com.br.canalvinciusthiengo.model.LastVideo
import thiengo.com.br.canalvinciusthiengo.ui.fragment.LastVideoFragment

/**
 * Responsável por informar ao fragmento
 * LastVideoFragment que dados de um novo
 * "último vídeo" do canal YouTube do app já
 * estão disponíveis.
 *
 * Assim que esses novos dados são salvos em
 * base local eles são em seguida enviados,
 * via LocalBroadcastManager, ao objeto desta
 * classe, NewLastVideoBroadcast. O
 * recebimento somente ocorre se o aplicativo
 * estiver em foreground, caso contrário nada
 * chega ao objeto desta classe, pois ele nem
 * mesmo existe.
 *
 * @property fragment fragmento que contém a
 * UI com os dados de último vídeo disponível.
 * @constructor cria um objeto completo do tipo
 * NewLastVideoBroadcast.
 */
class NewLastVideoBroadcast(
        private val fragment: LastVideoFragment
    ): BroadcastReceiver() {

    companion object{
        /**
         * Constante com o identificador único
         * do objeto broadcast
         * NewLastVideoBroadcast em lista de
         * LocalBrodcasts registrados em app.
         */
        const val FILTER_KEY = "LocalBroadcastLastVideo_key"

        /**
         * Constante com o identificador único
         * do dado (objeto LastVideo) presente
         * no objeto Intent que é recebido em
         * onReceive().
         */
        const val DATA_KEY = "LastVideo_key"
    }

    /**
     * Método receptor de dados da chamada ao
     * objeto broadcast do tipo NewLastVideoBroadcast.
     *
     * @param context contexto do aplicativo.
     * @param data Intent com dados enviados
     * ao objeto broadcast.
     */
    override fun onReceive(
        context: Context,
        data: Intent ){

        val lastVideo = data
            .getParcelableExtra<LastVideo>( DATA_KEY )!!

        fragment.newLastVideoData( lVideo = lastVideo )
    }
}