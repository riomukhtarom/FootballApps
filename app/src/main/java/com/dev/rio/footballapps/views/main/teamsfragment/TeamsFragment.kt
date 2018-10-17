package com.dev.rio.footballapps.views.main.teamsfragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import com.dev.rio.footballapps.R
import com.dev.rio.footballapps.api.ApiRepository
import com.dev.rio.footballapps.models.team.Team
import com.dev.rio.footballapps.presenters.TeamsPresenter
import com.dev.rio.footballapps.views.detail.detailteam.DetailTeamActivity
import com.dev.rio.footballapps.views.main.TeamView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_teams.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class TeamsFragment : Fragment(), TeamView, SearchView.OnQueryTextListener{

    private var teamItems: MutableList<Team> = mutableListOf()
    private var searchTeamItems: MutableList<Team> = mutableListOf()
    private lateinit var teamsPresenter: TeamsPresenter
    private lateinit var teamsAdapter: TeamsAdapter
    private val api = ApiRepository()
    private val gson = Gson()
    private lateinit var leagueName: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)

        val spinnerItems = resources.getStringArray(R.array.league_name)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner_teams.adapter = spinnerAdapter

        teamsPresenter = TeamsPresenter(this, api, gson)
        spinner_teams.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner_teams.selectedItem.toString()
                teamsPresenter.getTeamList(leagueName)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        teamsAdapter = TeamsAdapter(activity!!,teamItems){
            startActivity<DetailTeamActivity>("id" to "${it.teamId}")
        }
        rv_teams.layoutManager = LinearLayoutManager(activity)
        rv_teams.adapter = teamsAdapter

        sr_teams.onRefresh {
            sr_teams.isRefreshing = false
            teamsPresenter.getTeamList(leagueName)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun showLoading() {
        pb_teams.visibility = ProgressBar.VISIBLE
    }

    override fun hideLoading() {
        pb_teams.visibility = ProgressBar.INVISIBLE
    }

    override fun showTeam(data: List<Team>) {
        teamItems.clear()
        teamItems.addAll(data)
        searchTeamItems.clear()
        searchTeamItems.addAll(data)
        teamsAdapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.search_menu, menu)
        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        searchView.queryHint = "Search..."

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText!!.isNotEmpty()){
            teamItems.clear()
            val search = newText.toLowerCase()
            searchTeamItems.forEach{
                if(it.teamName!!.toLowerCase().contains(search)){
                    teamItems.add(it)
                }
            }
        } else {
            teamItems.clear()
            teamItems.addAll(searchTeamItems)
        }
        teamsAdapter.notifyDataSetChanged()
        return true
    }


}
