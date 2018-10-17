package com.dev.rio.footballapps.views.main.favoritesfragment.teamsfavorites


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.rio.footballapps.R
import com.dev.rio.footballapps.databases.FavoriteTeam
import com.dev.rio.footballapps.databases.databaseFavoriteTeam
import com.dev.rio.footballapps.views.detail.detailteam.DetailTeamActivity
import kotlinx.android.synthetic.main.fragment_teams_favorites.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class TeamsFavoritesFragment : Fragment() {

    private var teamItems: MutableList<FavoriteTeam> = mutableListOf()
    private lateinit var teamsFavoritesAdapter: TeamsFavoritesAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        teamsFavoritesAdapter = TeamsFavoritesAdapter(activity!!, teamItems){
            startActivity<DetailTeamActivity>("id" to "${it.teamId}")
        }

        rv_team_favorites.layoutManager = LinearLayoutManager(activity)
        rv_team_favorites.adapter = teamsFavoritesAdapter

        teamItems.clear()
        showFavoriteTeam()

        sr_team_favorites.onRefresh {
            teamItems.clear()
            showFavoriteTeam()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teams_favorites, container, false)
    }

    private fun showFavoriteTeam(){
        context?.databaseFavoriteTeam?.use {
            sr_team_favorites.isRefreshing = false
            val result = select(FavoriteTeam.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            teamItems.addAll(favorite)
            teamsFavoritesAdapter.notifyDataSetChanged()
        }
    }


}
