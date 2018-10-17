package com.dev.rio.footballapps.views.main.matchesfragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.rio.footballapps.R
import kotlinx.android.synthetic.main.fragment_matches.*

class MatchesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_matches, container, false)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        view_pager_matches.adapter = MatchesPagerAdapter(activity!!.supportFragmentManager)
        tab_layout_matches.setupWithViewPager(view_pager_matches)
    }

}
