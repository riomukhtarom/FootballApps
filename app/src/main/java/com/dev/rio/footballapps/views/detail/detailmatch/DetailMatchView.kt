package com.dev.rio.footballapps.views.detail.detailmatch

import com.dev.rio.footballapps.models.match.Match
import com.dev.rio.footballapps.models.team.Team

interface  DetailMatchView {
    fun showLoading()
    fun hideLoading()
    fun showDetailMatch(events: List<Match>, homeTeam: List<Team>, awayTeam: List<Team>)
}