package com.dev.rio.footballapps.presenters

import com.dev.rio.footballapps.CoroutineContextProvider
import com.dev.rio.footballapps.api.ApiRepository
import com.dev.rio.footballapps.api.TheSportDBApi
import com.dev.rio.footballapps.models.team.TeamResponse
import com.dev.rio.footballapps.views.detail.detailteam.DetailTeamView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailTeamPresenter(private val view: DetailTeamView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson,
                          private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamDetail(teamId: String) {
        view.showLoading()

        async(context.main){
            val data =  bg{
                gson.fromJson(
                        apiRepository.request(TheSportDBApi.getTeamDetail(teamId)),
                        TeamResponse::class.java)
            }
            view.showTeamDetail(data.await().teams)
            view.hideLoading()
        }
    }
}