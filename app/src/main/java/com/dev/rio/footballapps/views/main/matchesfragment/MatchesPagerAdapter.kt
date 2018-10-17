package com.dev.rio.footballapps.views.main.matchesfragment

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.dev.rio.footballapps.views.main.matchesfragment.lastmatches.LastMatchesFragment
import com.dev.rio.footballapps.views.main.matchesfragment.nextmatches.NextMatchesFragment

class MatchesPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm){

    private val pages = listOf(
            NextMatchesFragment(),
            LastMatchesFragment()
    )
    override fun getItem(position: Int): Fragment {
        return pages[position] as Fragment
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "NEXT"
            else -> "LAST"
        }
    }

}