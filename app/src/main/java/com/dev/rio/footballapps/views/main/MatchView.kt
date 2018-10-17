package com.dev.rio.footballapps.views.main

import com.dev.rio.footballapps.models.match.Match

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatch(matchList: List<Match>)
}