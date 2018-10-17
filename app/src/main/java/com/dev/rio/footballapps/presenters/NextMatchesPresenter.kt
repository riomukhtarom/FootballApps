package com.dev.rio.footballapps.presenters

import com.dev.rio.footballapps.CoroutineContextProvider
import com.dev.rio.footballapps.api.ApiRepository
import com.dev.rio.footballapps.api.TheSportDBApi
import com.dev.rio.footballapps.models.match.MatchResponse
import com.dev.rio.footballapps.views.main.MatchView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class NextMatchesPresenter (private val view: MatchView,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson,
                            private val context: CoroutineContextProvider = CoroutineContextProvider())  {

    fun getMatchList(event: String, leagueId: String){
        view.showLoading()
        async(context.main){
            val eventData = bg {
                gson.fromJson(
                        apiRepository.request(TheSportDBApi.getEventList(event, leagueId)),
                        MatchResponse::class.java
                )
            }
            view.hideLoading()
            view.showMatch(eventData.await().events)
        }
    }
}