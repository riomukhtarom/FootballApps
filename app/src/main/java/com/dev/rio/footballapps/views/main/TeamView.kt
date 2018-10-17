package com.dev.rio.footballapps.views.main

import com.dev.rio.footballapps.models.team.Team

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun showTeam(matchList: List<Team>)
}