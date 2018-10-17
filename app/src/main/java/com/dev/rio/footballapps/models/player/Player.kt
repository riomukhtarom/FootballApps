package com.dev.rio.footballapps.models.player

import com.google.gson.annotations.SerializedName

data class Player (
        @SerializedName("idPlayer")
        var playerId: String? = null,

        @SerializedName("strPlayer")
        var playerName: String? = null,

        @SerializedName("strCutout")
        var playerPhotoCut: String? = null,

        @SerializedName("strThumb")
        var playerPhotoDetail: String? = null,

        @SerializedName("strFanart1")
        var playerFanArt: String? = null,

        @SerializedName("strWeight")
        var playerWeight: String? = null,

        @SerializedName("strHeight")
        var playerHeight: String? = null,

        @SerializedName("strPosition")
        var playerPosition: String? = null,

        @SerializedName("strDescriptionEN")
        var playerDescription: String? = null
)