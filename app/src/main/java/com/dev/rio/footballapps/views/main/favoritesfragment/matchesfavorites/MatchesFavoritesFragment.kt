package com.dev.rio.footballapps.views.main.favoritesfragment.matchesfavorites


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.rio.footballapps.R
import com.dev.rio.footballapps.databases.FavoriteMatch
import com.dev.rio.footballapps.databases.databaseFavoriteMatch
import com.dev.rio.footballapps.views.detail.detailmatch.DetailMatchActivity
import kotlinx.android.synthetic.main.fragment_matches_favorites.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity


class MatchesFavoritesFragment : Fragment() {

    private val matchItems: MutableList<FavoriteMatch> = mutableListOf()
    private lateinit var favoriteMatchAdapter: MatchesFavoritesAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        favoriteMatchAdapter = MatchesFavoritesAdapter(activity!!, matchItems) {
            startActivity<DetailMatchActivity>(
                    "matchId" to it.matchId,
                    "homeId" to it.homeId,
                    "awayId" to it.awayId)
        }
        rv_matches_favorites.layoutManager = LinearLayoutManager(activity)
        rv_matches_favorites.adapter = favoriteMatchAdapter

        matchItems.clear()
        showFavoriteMatch()

        sr_matches_favorites.onRefresh {
            matchItems.clear()
            showFavoriteMatch()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_matches_favorites, container, false)
    }

    private fun showFavoriteMatch(){
        context?.databaseFavoriteMatch?.use {
            sr_matches_favorites.isRefreshing = false
            val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
            val favorite = result.parseList(classParser<FavoriteMatch>())
            matchItems.addAll(favorite)
            favoriteMatchAdapter.notifyDataSetChanged()
        }
    }


}
