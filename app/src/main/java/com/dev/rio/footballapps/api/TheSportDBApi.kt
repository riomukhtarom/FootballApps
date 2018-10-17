package com.dev.rio.footballapps.api

import com.dev.rio.footballapps.BuildConfig

object TheSportDBApi {
    fun getEventList(event: String?, leagueId: String?): String{
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.API_KEY}/${event}?id=${leagueId}"
    }

    fun getEventDetails(eventId: String?): String{
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.API_KEY}/lookupevent.php?id=${eventId}"
    }

    fun getTeamDetail(teamId: String?): String{
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.API_KEY}/lookupteam.php?id=${teamId}"
    }

    fun getTeamList(league: String?): String {
        return "${BuildConfig.BASE_URL}/api/v1/json/${BuildConfig.API_KEY}/search_all_teams.php?l=${league}"
    }

    fun getPlayerList(teamId: String?): String {
        return "${BuildConfig.BASE_URL}/api/v1/json/${BuildConfig.API_KEY}/lookup_all_players.php?id=${teamId}"
    }

    fun getPlayerDetail(playerId: String?): String {
        return "${BuildConfig.BASE_URL}/api/v1/json/${BuildConfig.API_KEY}/lookupplayer.php?id=${playerId}"
    }

}