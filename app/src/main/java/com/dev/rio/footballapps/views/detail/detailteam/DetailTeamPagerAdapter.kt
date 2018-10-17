package com.dev.rio.footballapps.views.detail.detailteam

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.dev.rio.footballapps.views.detail.detailteam.overviewfragment.OverviewTeamFragment
import com.dev.rio.footballapps.views.detail.detailteam.playerlistfragment.PlayerListFragment

class DetailTeamPagerAdapter(fm: FragmentManager, key: String, value: String) : FragmentStatePagerAdapter(fm){
    private var fragment1 = OverviewTeamFragment()
    private var fragment2 = PlayerListFragment()

    init {
        val bundle = Bundle()
        bundle.putString(key, value)
        fragment1.arguments = bundle
        fragment2.arguments = bundle
    }

    private val pages = listOf(
            fragment1,
            fragment2
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "OVERVIEW"
            else -> "PLAYER"
        }
    }
}