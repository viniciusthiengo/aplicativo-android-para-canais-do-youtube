package thiengo.com.br.canalvinciusthiengo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import thiengo.com.br.canalvinciusthiengo.adapter.MenuAdapter
import thiengo.com.br.canalvinciusthiengo.domain.MenuItem
import thiengo.com.br.canalvinciusthiengo.domain.MenuItemsData
import thiengo.com.br.canalvinciusthiengo.fragment.*


class MainActivity : AppCompatActivity() {

    companion object{
        const val LOG_TAG = "log_channel_app"
        const val FRAG_KEY = "frag"
        var APP_FOREGROUND = AppForegroundStatus.IS_NOT_IN_FORGROUND
    }

    override fun onCreate( savedInstanceState: Bundle? ) {
        super.onCreate( savedInstanceState )
        setContentView( R.layout.activity_main )

        if( supportFragmentManager.findFragmentByTag( FRAG_KEY ) == null ) {
            changeFragment( LastVideoFragment(), getFragmentInKey() )
        }

        initItineraryMenu()
    }

    override fun onResume() {
        super.onResume()
        APP_FOREGROUND = AppForegroundStatus.IS_IN_FORGROUND
    }

    override fun onPause() {
        super.onPause()
        APP_FOREGROUND = AppForegroundStatus.IS_NOT_IN_FORGROUND
    }

    private fun initItineraryMenu(){

        val layoutManager = GridLayoutManager(
            this,
            MenuItemsData.NUMBER_COLUMNS,
            RecyclerView.HORIZONTAL,
            false
        )
        rv_menu.layoutManager = layoutManager

        rv_menu.setHasFixedSize( true )
        rv_menu.adapter = MenuAdapter(
            context = this,
            items = MenuItemsData.getItems( resources ),
            changeFragmentCallback = {
                item -> fragmentOnScreen( item )
            }
        )
    }

    private fun fragmentOnScreen( item: MenuItem ){
        val fragment = getFragment( item.id )

        changeFragment(
            fragment,
            getFragmentInKey( item.id )
        )
    }

    private fun getFragment( itemId: Int = R.id.last_video ) : Fragment {

        val key = getFragmentInKey( itemId )
        var fragment = supportFragmentManager.findFragmentByTag( key )

        if( fragment == null ){
            fragment = when( itemId ){
                R.id.social_networks -> SocialNetworksFragment()
                R.id.play_lists -> PlayListsFragment()
                R.id.exclusive_groups -> GroupsFragment()
                R.id.last_info -> LastInfoFragment()
                R.id.about_channel -> AboutChannelFragment()
                R.id.books -> BooksFragment()
                R.id.courses -> CoursesFragment()
                R.id.business -> BusinessContactsFragment()
                else -> LastVideoFragment()
            }
        }

        return fragment
    }

    private fun getFragmentInKey( itemId: Int = R.id.last_video )
        = when( itemId ){
            R.id.social_networks -> SocialNetworksFragment.KEY
            R.id.play_lists -> PlayListsFragment.KEY
            R.id.exclusive_groups -> GroupsFragment.KEY
            R.id.last_info -> LastInfoFragment.KEY
            R.id.about_channel -> AboutChannelFragment.KEY
            R.id.books -> BooksFragment.KEY
            R.id.courses -> CoursesFragment.KEY
            R.id.business -> BusinessContactsFragment.KEY
            else -> LastVideoFragment.KEY
        }

    private fun changeFragment( fragment: Fragment, fragKey: String ){

        val fragTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()

        fragTransaction.setCustomAnimations(
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )
        //fragmentManager.addOnBackStackChangedListener(this)
        fragTransaction.replace( R.id.ll_content_container, fragment, fragKey )
        fragTransaction.addToBackStack( FRAG_KEY )
        fragTransaction.commit()
    }

    override fun onBackPressed() {
        supportFragmentManager.popBackStack(
            null,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
        super.onBackPressed()
    }
}