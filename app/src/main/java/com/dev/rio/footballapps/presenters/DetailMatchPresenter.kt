package com.dev.rio.footballapps.presenters

import com.dev.rio.footballapps.CoroutineContextProvider
import com.dev.rio.footballapps.api.ApiRepository
import com.dev.rio.footballapps.api.TheSportDBApi
import com.dev.rio.footballapps.models.match.MatchResponse
import com.dev.rio.footballapps.models.team.TeamResponse
import com.dev.rio.footballapps.views.detail.detailmatch.DetailMatchView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailMatchPresenter(
        private val view: DetailMatchView,
        private val apiRepository: ApiRepository,
        private val gson: Gson,
        private val context: CoroutineContextProvider = CoroutineContextProvider()){

    fun getMatchDetail(matchId: String?, homeTeamId: String?, awayTeamId: String?){
        view.showLoading()

        async(context.main){
            val eventDetail = bg {
                gson.fromJson(
                        apiRepository.request(TheSportDBApi.getEventDetails(matchId)),
                        MatchResponse::class.java
                )
            }
            val homeTeamLogo = bg {
                gson.fromJson(
                        apiRepository.request(TheSportDBApi.getTeamDetail(homeTeamId)),
                        TeamResponse::class.java
                )
            }
            val awayTeamLogo = bg {
                gson.fromJson(
                        apiRepository.request(TheSportDBApi.getTeamDetail(awayTeamId)),
                        TeamResponse::class.java
                )
            }
            view.hideLoading()
            view.showDetailMatch(eventDetail.await().events, homeTeamLogo.await().teams, awayTeamLogo.await().teams)
        }
    }
}