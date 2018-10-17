package com.dev.rio.footballapps.views.main.matchesfragment.lastmatches


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
import com.dev.rio.footballapps.models.match.Match
import com.dev.rio.footballapps.presenters.LastMatchesPresenter
import com.dev.rio.footballapps.views.detail.detailmatch.DetailMatchActivity
import com.dev.rio.footballapps.views.main.MatchView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_last_matches.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity


class LastMatchesFragment : Fragment(), MatchView, SearchView.OnQueryTextListener {

    private val matchItems: MutableList<Match> = mutableListOf()
    private val searchMatchItems: MutableList<Match> = mutableListOf()
    private lateinit var lastMatchesAdapter: LastMatchesAdapter
    private lateinit var lastMatchesPresenter: LastMatchesPresenter
    private lateinit var leagueId: String
    private val LAST_EVENT: String = "eventspastleague.php"
    private val api = ApiRepository()
    private val gson = Gson()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setHasOptionsMenu(true)

        val spinnerItems = resources.getStringArray(R.array.league_name)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner_last_matches.adapter = spinnerAdapter

        lastMatchesPresenter = LastMatchesPresenter(this, api, gson)
        spinner_last_matches.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val leagueItems = resources.getStringArray(R.array.league_id)
                leagueId = leagueItems[position]
                lastMatchesPresenter.getMatchList(LAST_EVENT, leagueId)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }

        lastMatchesAdapter = LastMatchesAdapter(activity!!, matchItems) {
            startActivity<DetailMatchActivity>(
                    "matchId" to it.matchId,
                    "homeId" to it.homeId,
                    "awayId" to it.awayId)
        }
        rv_last_matches.layoutManager = LinearLayoutManager(activity)
        rv_last_matches.adapter = lastMatchesAdapter

        sr_last_matches.onRefresh {
            sr_last_matches.isRefreshing = false
            lastMatchesPresenter.getMatchList(LAST_EVENT, leagueId)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_last_matches, container, false)
    }

    override fun showLoading() {
        pb_last_matches.visibility = ProgressBar.VISIBLE
    }

    override fun hideLoading() {
        pb_last_matches.visibility = ProgressBar.INVISIBLE
    }

    override fun showMatch(matchList: List<Match>) {
        matchItems.clear()
        matchItems.addAll(matchList)
        searchMatchItems.clear()
        searchMatchItems.addAll(matchList)
        lastMatchesAdapter.notifyDataSetChanged()
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
            matchItems.clear()
            val search = newText.toLowerCase()
            searchMatchItems.forEach{
                if(it.homeTeam!!.toLowerCase().contains(search)||it.awayTeam!!.toLowerCase().contains(search)){
                    matchItems.add(it)
                }
            }
        } else {
            matchItems.clear()
            matchItems.addAll(searchMatchItems)
        }
        lastMatchesAdapter.notifyDataSetChanged()
        return true
    }


}
