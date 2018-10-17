package com.dev.rio.footballapps.models.match

import com.google.gson.annotations.SerializedName
import java.util.*


data class Match (

        @SerializedName("idEvent")
        var matchId: String? = null,

        @SerializedName("dateEvent")
        var matchDate: Date? = null,

        @SerializedName("strTime")
        var matchTime: String? = null,

        //home
        @SerializedName("idHomeTeam")
        var homeId: String? = null,

        @SerializedName("strHomeTeam")
        var homeTeam: String? = null,

        @SerializedName("intHomeScore")
        var homeScore: String? = null,

        @SerializedName("strHomeFormation")
        var homeFormation: String? = null,

        @SerializedName("strHomeGoalDetails")
        var homeGoalDetails: String? = null,

        @SerializedName("intHomeShots")
        var homeShots: String? = null,

        @SerializedName("strHomeLineupGoalkeeper")
        var homeGoalKeeper: String? = null,

        @SerializedName("strHomeLineupDefense")
        var homeDefense: String? = null,

        @SerializedName("strHomeLineupMidfield")
        var homeMidfield: String? = null,

        @SerializedName("strHomeLineupForward")
        var homeForward: String? = null,

        //away
        @SerializedName("idAwayTeam")
        var awayId: String? = null,

        @SerializedName("strAwayTeam")
        var awayTeam: String? = null,

        @SerializedName("intAwayScore")
        var awayScore: String? = null,

        @SerializedName("strAwayFormation")
        var awayFormation: String? = null,

        @SerializedName("strAwayGoalDetails")
        var awayGoalDetails: String? = null,

        @SerializedName("intAwayShots")
        var awayShots: String? = null,

        @SerializedName("strAwayLineupGoalkeeper")
        var awayGoalKeeper: String? = null,

        @SerializedName("strAwayLineupDefense")
        var awayDefense: String? = null,

        @SerializedName("strAwayLineupMidfield")
        var awayMidfield: String? = null,

        @SerializedName("strAwayLineupForward")
        var awayForward: String? = null
)