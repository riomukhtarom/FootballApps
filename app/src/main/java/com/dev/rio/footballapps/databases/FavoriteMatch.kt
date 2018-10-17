package com.dev.rio.footballapps.databases


data class FavoriteMatch (
        val id: Long?,
        val matchId: String?,
        val matchDate: String?,
        val matchTime: String?,

        //home
        val homeId: String?,
        val homeTeam: String?,
        val homeScore: String?,

        //away
        val awayId: String?,
        val awayTeam: String?,
        val awayScore: String?) {

    companion object {
        const val TABLE_FAVORITE_MATCH: String = "TABLE_FAVORITE_MATCH"
        const val ID: String = "ID_"
        const val MATCH_ID: String = "MATCH_ID"
        const val MATCH_DATE: String = "MATCH_DATE"
        const val MATCH_TIME: String = "MATCH_TIME"

        //HOME
        const val HOME_ID: String = "HOME_ID"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val HOME_SCORE: String = "HOME_SCORE"

        //AWAY
        const val AWAY_ID: String = "AWAY_ID"
        const val AWAY_TEAM: String = "AWAY_TEAM"
        const val AWAY_SCORE: String = "AWAY_SCORE"
    }
}