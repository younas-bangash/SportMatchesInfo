package com.sport.matchesinfo.data

import com.google.gson.annotations.SerializedName

/**
 * Data class that represents Match Information
 */
data class MatchDetails(
    @field:SerializedName("Team_A") val firstTeamName: String,
    @field:SerializedName("Team_B") val secondTeamName: String,
    @field:SerializedName("Score") val score: String,
    @field:SerializedName("link_A") val firstTeamImg: String,
    @field:SerializedName("link_B") val secondTeamImg: String,
    @field:SerializedName("Date") val matchDate: String
)
