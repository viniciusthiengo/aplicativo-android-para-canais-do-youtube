package thiengo.com.br.canalvinciusthiengo.ui

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import thiengo.com.br.canalvinciusthiengo.R
import thiengo.com.br.canalvinciusthiengo.config.YouTubeConfig
import thiengo.com.br.canalvinciusthiengo.data.fixed.MenuItemsData
import thiengo.com.br.canalvinciusthiengo.model.MenuItem
import thiengo.com.br.canalvinciusthiengo.ui.adapter.MenuAdapter
import thiengo.com.br.canalvinciusthiengo.ui.fragment.*

/**
 * Atividade principal e única atividade do
 * aplicativo.
 *
 * @constructor cria um objeto completo do tipo
 * MainActivity.
 */
class MainActivity : AppCompatActivity() {

    companion object{
        const val LOG_TAG = "log_channel_app"
        /**
         * Constante com o identificador único da
         * pilha de fragmentos em memória.
         */
        const val FRAG_STACK_ID = "frag_stack_id"

        /**
         * Propriedade estática que mantém o estado
         * atual da atividade. Se ela está ou não em
         * foreground. Está propriedade é acessada
         * em outras partes do aplicativo que
         * dependem desta informação.
         */
        var APP_FOREGROUND = MainActivityForegroundStatus.IS_NOT_IN_FOREGROUND
    }

    override fun onCreate( savedInstanceState: Bundle? ){
        setTheme( R.style.AppTheme )
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_main )

        if( supportFragmentManager.findFragmentByTag( FRAG_STACK_ID ) == null ){
            changeFragment(
                fragment = LastVideoFragment(),
                fragKey = getFragmentInKey()
            )
        }

        initBottomMenu()
    }

    override fun onResume() {
        super.onResume()
        removeStatusBarNotification()
        APP_FOREGROUND = MainActivityForegroundStatus.IS_IN_FOREGROUND
    }

    override fun onPause() {
        super.onPause()
        APP_FOREGROUND = MainActivityForegroundStatus.IS_NOT_IN_FOREGROUND
    }

    override fun onBackPressed(){
        /**
         * O código abaixo limpa a pilha de
         * fragmentos do app para que seja
         * possível deixar (fechar) o aplicativo
         * sem a necessidade de voltar para cada
         * um dos fragmentos já navegados pelo
         * usuário.
         */
        supportFragmentManager
            .popBackStack(
                null,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )

        super.onBackPressed()
    }

    /**
     * Garante que não haverá notificação do aplicativo
     * na bandeja de notificações do aparelho quando o
     * app for aberto.
     */
    private fun removeStatusBarNotification(){
        val notificationManager = getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager

        notificationManager.cancelAll()
    }

    /**
     * Inicializa o menu principal do aplicativo, o
     * BottomMenu.
     */
    private fun initBottomMenu(){

        val layoutManager = GridLayoutManager(
            this,
            MenuAdapter.NUMBER_COLUMNS,
            RecyclerView.HORIZONTAL,
            false
        )
        rv_menu.layoutManager = layoutManager

        rv_menu.setHasFixedSize( true )
        rv_menu.adapter = MenuAdapter(
            context = this,
            items = MenuItemsData.getItems( res = resources ),
            changeFragmentCallback = {
                item -> fragmentOnScreen( item = item )
            }
        )
    }

    /**
     * Coloca em tela o fragmento correto de acordo
     * com o item de menu informado como parâmetro.
     *
     * @param item item de menu.
     */
    private fun fragmentOnScreen( item: MenuItem ){
        val fragment = getFragment( itemId = item.id )
        val fragKey = getFragmentInKey( itemId = item.id )

        changeFragment(
            fragment = fragment,
            fragKey = fragKey
        )
    }

    /**
     * Retorna o fragmento correto de acordo com o
     * identificador único de item de menu informado
     * como parâmetro.
     *
     * Se o fragmento estiver em pilha de fragmentos,
     * memória, então ele é obtido da pilha ao invés
     * de ser criado um novo. Diminuindo assim a
     * possibilidade de vazamento de memória.
     *
     * @param itemId identificador único de item de
     * menu.
     * @return objeto fragmento correto.
     */
    private fun getFragment(
        itemId: Int = R.id.last_video ) : Fragment {

        val key = getFragmentInKey( itemId = itemId )

        var fragment = supportFragmentManager
            .findFragmentByTag( key )

        if( fragment == null ){
            fragment = when( itemId ){
                R.id.social_networks -> SocialNetworksFragment()
                R.id.play_lists -> PlayListsFragment()
                R.id.exclusive_groups -> GroupsFragment()
                R.id.about_channel -> AboutChannelFragment()
                R.id.books -> BooksFragment()
                R.id.courses -> CoursesFragment()
                R.id.business -> BusinessContactsFragment()
                else -> LastVideoFragment()
            }
        }

        return fragment
    }

    /**
     * Retorna o identificador único (id) de
     * fragmento em memória de acordo com o
     * identificador único de item de menu
     * informado como parâmetro.
     *
     * Com o id de fragmento é possível verificar
     * se ele já está em pilha de fragmentos ou
     * não.
     *
     * @param itemId identificador único de item de
     * menu.
     * @return identificador único do fragmento.
     */
    private fun getFragmentInKey(
        itemId: Int = R.id.last_video )
        = when( itemId ){
            R.id.social_networks -> SocialNetworksFragment.KEY
            R.id.play_lists -> PlayListsFragment.KEY
            R.id.exclusive_groups -> GroupsFragment.KEY
            R.id.about_channel -> AboutChannelFragment.KEY
            R.id.books -> BooksFragment.KEY
            R.id.courses -> CoursesFragment.KEY
            R.id.business -> BusinessContactsFragment.KEY
            else -> LastVideoFragment.KEY
        }

    /**
     * Muda o fragmento que deve ficar em foreground
     * (primeiro plano).
     *
     * @param fragment fragmento que deverá ficar
     * em foreground.
     * @param fragKey identificador único do
     * fragmento que vai ficar em foreground.
     */
    private fun changeFragment(
        fragment: Fragment,
        fragKey: String ){

        /**
         * Animação na transição entre fragmentos.
         */
        val fragTransaction = supportFragmentManager.beginTransaction()
        fragTransaction.setCustomAnimations(
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )

        /**
         * Mantendo o fragmento em memória com um
         * "localizador" único dele.
         */
        fragTransaction.replace(
            R.id.ll_content_container,
            fragment,
            fragKey
        )
        fragTransaction.addToBackStack( FRAG_STACK_ID )

        fragTransaction.commit()
    }

    /**
     * Invoca o aplicativo do YouTube para que o usuário
     * tenha acesso direto ao canal.
     *
     * Esse listener de clique está vinculado aos
     * componentes visuais do topo do aplicativo.
     *
     * Caso o aplicativo nativo do YouTube e nem mesmo um
     * navegador Web esteja instalado no aparelho (algo
     * utópico), então uma mensagem de falha é apresentada
     * ao usuário.
     *
     * @param view componente visual que teve o evento de
     * toque (clique) disparado.
     */
    fun openYouTubeChannel( view: View ){
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse( YouTubeConfig.Channel.CHANNEL_URL )
        )

        if( intent.resolveActivity( packageManager ) != null ){
            startActivity( intent )
        }
        else{
            Toast
                .makeText(
                    this,
                    getString( R.string.channel_toast_alert ),
                    Toast.LENGTH_LONG
                )
                .show()
        }
    }
}