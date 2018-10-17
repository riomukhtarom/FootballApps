package com.dev.rio.footballapps.views.detail.detailteam.playerlistfragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.dev.rio.footballapps.views.detail.detailplayer.DetailPlayerActivity
import com.dev.rio.footballapps.R
import com.dev.rio.footballapps.api.ApiRepository
import com.dev.rio.footballapps.models.player.Player
import com.dev.rio.footballapps.presenters.PlayerListPresenter
import com.dev.rio.footballapps.views.detail.detailplayer.DetailPlayerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_player_list.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity


class PlayerListFragment : Fragment(), DetailPlayerView {

    private var playerItems: MutableList<Player> = mutableListOf()
    private lateinit var playerListPresenter: PlayerListPresenter
    private lateinit var playerListAdapter: PlayerListAdapter
    private val api = ApiRepository()
    private val gson = Gson()
    private lateinit var teamId: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        playerListPresenter = PlayerListPresenter(this, api, gson)
        playerListPresenter.getPlayerList(teamId)

        playerListAdapter = PlayerListAdapter(activity!!,playerItems){
            startActivity<DetailPlayerActivity>("id" to "${it.playerId}")
        }
        rv_player_list.layoutManager = LinearLayoutManager(activity)
        rv_player_list.adapter = playerListAdapter

        sr_player_list.onRefresh {
            sr_player_list.isRefreshing = false
            playerListPresenter.getPlayerList(teamId)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val bundle = this.arguments
        teamId = bundle?.getString("id").toString()
        return inflater.inflate(R.layout.fragment_player_list, container, false)
    }

    override fun showLoading() {
        pb_player_list.visibility = ProgressBar.VISIBLE
    }

    override fun hideLoading() {
        pb_player_list.visibility = ProgressBar.INVISIBLE
    }

    override fun showPlayerDetail(data: List<Player>) {
        playerItems.clear()
        playerItems.addAll(data)
        playerListAdapter.notifyDataSetChanged()
    }


}
