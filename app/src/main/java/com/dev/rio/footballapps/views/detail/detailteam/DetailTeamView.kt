package com.dev.rio.footballapps.views.detail.detailteam

import com.dev.rio.footballapps.models.team.Team

interface DetailTeamView {
    fun showLoading()
    fun hideLoading()
    fun showTeamDetail(data: List<Team>)
}