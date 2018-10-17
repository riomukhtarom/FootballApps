package com.dev.rio.footballapps.presenters

import com.dev.rio.footballapps.TestContextProvider
import com.dev.rio.footballapps.api.ApiRepository
import com.dev.rio.footballapps.api.TheSportDBApi
import com.dev.rio.footballapps.models.match.Match
import com.dev.rio.footballapps.models.match.MatchResponse
import com.dev.rio.footballapps.views.main.MatchView
import com.google.gson.Gson
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class NextMatchesPresenterTest {

    @Mock
    private lateinit var view: MatchView

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var gson: Gson


    private lateinit var nextMatchPresenter: NextMatchesPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        nextMatchPresenter = NextMatchesPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun getMatchList() {
        val matchs: MutableList<Match> = mutableListOf()
        val matchResponse = MatchResponse(matchs)
        val event = "eventsnextleague.php"
        val leagueId = "4328"

        `when`(gson.fromJson(
                apiRepository.request(TheSportDBApi.getEventList(event, leagueId)),
                MatchResponse::class.java
        )).thenReturn(matchResponse)

        nextMatchPresenter.getMatchList(event, leagueId)

        verify(view).showLoading()
        verify(view).hideLoading()
        verify(view).showMatch(matchs)
    }
}