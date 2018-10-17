package com.dev.rio.footballapps.views.detail.detailplayer

import com.dev.rio.footballapps.models.player.Player

interface DetailPlayerView {
    fun showLoading()
    fun hideLoading()
    fun showPlayerDetail(data: List<Player>)
}