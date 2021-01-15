package com.allysonjeronimo.rupi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.allysonjeronimo.rupi.extensions.addFragment
import com.allysonjeronimo.rupi.ui.converter.ConverterFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.toolbar_main.*

class MainActivity : AppCompatActivity() {

    private val drawerToggle:ActionBarDrawerToggle by lazy{
        ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.app_name, R.string.app_name)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            addFragment(R.id.container, ConverterFragment.newInstance())
        }

        setupToolbar()
    }

    private fun setupToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
        drawerToggle.isDrawerIndicatorEnabled = false
        drawerToggle.setHomeAsUpIndicator(R.drawable.ic_logo_toolbar)

        drawerToggle.setToolbarNavigationClickListener {
            if(drawer_layout.isDrawerOpen(GravityCompat.START)){
                drawer_layout.closeDrawer(GravityCompat.START)
            }
            else{
                drawer_layout.openDrawer(GravityCompat.START)
            }
        }

        navigation_view.setNavigationItemSelectedListener {
            menuItem -> selectMenuOption(menuItem)
            true
        }
    }

    private fun selectMenuOption(menuItem:MenuItem){
        if(menuItem.itemId == R.id.action_about){
            Snackbar.make(drawer_layout, R.string.action_about, Snackbar.LENGTH_SHORT).show()
        }
        if(menuItem.itemId == R.id.action_rating){
            Snackbar.make(drawer_layout, R.string.action_rating, Snackbar.LENGTH_SHORT).show()
        }
        if(menuItem.itemId == R.id.action_feedback){
            Snackbar.make(drawer_layout, R.string.action_feedback, Snackbar.LENGTH_SHORT).show()
        }
        if(menuItem.itemId == R.id.action_settings){
            Snackbar.make(drawer_layout, R.string.action_settings, Snackbar.LENGTH_SHORT).show()
        }
        drawer_layout.closeDrawers()
    }
}