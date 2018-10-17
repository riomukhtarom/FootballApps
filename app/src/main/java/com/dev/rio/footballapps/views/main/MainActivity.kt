package com.dev.rio.footballapps.views.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.dev.rio.footballapps.R
import com.dev.rio.footballapps.R.id.*
import com.dev.rio.footballapps.views.main.favoritesfragment.FavoritesFragment
import com.dev.rio.footballapps.views.main.matchesfragment.MatchesFragment
import com.dev.rio.footballapps.views.main.teamsfragment.TeamsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.elevation = 0F

        bottomNavigationMain.setOnNavigationItemSelectedListener { item ->
            when (item.itemId){
                item_matches -> {
                    deleteTeamsFragment()
                    loadMatchesFragment(savedInstanceState)
                }

                item_teams -> {
                    deleteTeamsFragment()
                    loadTeamsFragment(savedInstanceState)
                }

                item_favorites -> {
                    deleteTeamsFragment()
                    loadFavoritesFragment(savedInstanceState)
                }
            }

            true
        }

        bottomNavigationMain.selectedItemId = item_matches
    }

    private fun loadMatchesFragment(savedInstanceState: Bundle?){
        if(savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_mainFrame, MatchesFragment(), MatchesFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadTeamsFragment(savedInstanceState: Bundle?){
        if(savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_mainFrame, TeamsFragment(), TeamsFragment::class.java.simpleName)
                    .commit()
        }
    }
    private fun loadFavoritesFragment(savedInstanceState: Bundle?){
        if(savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_mainFrame, FavoritesFragment(), FavoritesFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun deleteTeamsFragment() {
        if(supportFragmentManager.fragments!=null && supportFragmentManager.fragments.size>0) {
            for (i in 0 until supportFragmentManager.fragments.size) {
                val mFragment = supportFragmentManager.fragments[i]
                if (mFragment != null) {
                    supportFragmentManager.beginTransaction().remove(mFragment).commit()
                }
            }
        }
    }

}
