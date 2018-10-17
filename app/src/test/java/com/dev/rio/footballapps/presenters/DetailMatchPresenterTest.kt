package com.dev.rio.footballapps.presenters

import com.dev.rio.footballapps.TestContextProvider
import com.dev.rio.footballapps.api.ApiRepository
import com.dev.rio.footballapps.api.TheSportDBApi
import com.dev.rio.footballapps.models.match.Match
import com.dev.rio.footballapps.models.match.MatchResponse
import com.dev.rio.footballapps.models.team.Team
import com.dev.rio.footballapps.models.team.TeamResponse
import com.dev.rio.footballapps.views.detail.detailmatch.DetailMatchView
import com.google.gson.Gson
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class DetailMatchPresenterTest {

    @Mock
    private lateinit var view: DetailMatchView

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var gson: Gson


    private lateinit var detailPresenter: DetailMatchPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        detailPresenter = DetailMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getMatchDetail() {
        val event: MutableList<Match> = mutableListOf()
        val homeTeam: MutableList<Team> = mutableListOf()
        val awayTeam: MutableList<Team> = mutableListOf()
        val eventResponse = MatchResponse(event)
        val homeTeamResponse = TeamResponse(homeTeam)
        val awayTeamResponse = TeamResponse(awayTeam)
        val matchId = "441613"
        val homeId = "133604"
        val awayId = "133605 "

        `when`(gson.fromJson(
                apiRepository.request(TheSportDBApi.getEventDetails(matchId)),
                MatchResponse::class.java
        )).thenReturn(eventResponse)

        `when`(gson.fromJson(
                apiRepository.request(TheSportDBApi.getTeamDetail(homeId)),
                TeamResponse::class.java
        )).thenReturn(homeTeamResponse)

        `when`(gson.fromJson(
                apiRepository.request(TheSportDBApi.getTeamDetail(awayId)),
                TeamResponse::class.java
        )).thenReturn(awayTeamResponse)

        detailPresenter.getMatchDetail(matchId, homeId, awayId)

        verify(view).showLoading()
        verify(view).hideLoading()
        verify(view).showDetailMatch(event, homeTeam, awayTeam)
    }
}