package com.dev.rio.footballapps.presenters

import com.dev.rio.footballapps.CoroutineContextProvider
import com.dev.rio.footballapps.api.ApiRepository
import com.dev.rio.footballapps.api.TheSportDBApi
import com.dev.rio.footballapps.models.player.PlayerResponse
import com.dev.rio.footballapps.views.detail.detailplayer.DetailPlayerView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PlayerListPresenter (private val view: DetailPlayerView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getPlayerList(league: String?){
        view.showLoading()

        async(context.main){
            val data =  bg{
                gson.fromJson(
                        apiRepository.request(TheSportDBApi.getPlayerList(league)),
                        PlayerResponse::class.java)
            }
            view.showPlayerDetail(data.await().player)
            view.hideLoading()
        }
    }
}