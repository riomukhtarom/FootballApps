package com.dev.rio.footballapps.views.detail.detailteam.overviewfragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.dev.rio.footballapps.R
import com.dev.rio.footballapps.api.ApiRepository
import com.dev.rio.footballapps.models.team.Team
import com.dev.rio.footballapps.presenters.OverviewDetailTeamPresenter
import com.dev.rio.footballapps.views.detail.detailteam.DetailTeamView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_overview_team.*

class OverviewTeamFragment : Fragment(), DetailTeamView {

    private lateinit var detailTeamPresenter: OverviewDetailTeamPresenter
    private lateinit var teamId: String

    val api = ApiRepository()
    val gson = Gson()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val bundle = this.arguments
        teamId = bundle?.getString("id").toString()
        return inflater.inflate(R.layout.fragment_overview_team, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        detailTeamPresenter = OverviewDetailTeamPresenter(this, api, gson)
        detailTeamPresenter.getTeamDetail(teamId)
    }

    override fun showLoading() {
        pb_detail_team_overview.visibility = ProgressBar.VISIBLE
    }

    override fun hideLoading() {
        pb_detail_team_overview.visibility = ProgressBar.INVISIBLE
    }

    override fun showTeamDetail(data: List<Team>) {
        tv_detail_team_overview.text = data[0].teamDescription
    }


}
