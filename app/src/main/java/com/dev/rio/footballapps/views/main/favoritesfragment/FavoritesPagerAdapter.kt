package com.dev.rio.footballapps.views.main.favoritesfragment

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.dev.rio.footballapps.views.main.favoritesfragment.matchesfavorites.MatchesFavoritesFragment
import com.dev.rio.footballapps.views.main.favoritesfragment.teamsfavorites.TeamsFavoritesFragment

class FavoritesPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm){

    private val pages = listOf(
            MatchesFavoritesFragment(),
            TeamsFavoritesFragment()
    )
    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "MATCHES"
            else -> "TEAMS"
        }
    }

}