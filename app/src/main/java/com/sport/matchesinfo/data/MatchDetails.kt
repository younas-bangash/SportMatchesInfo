package com.sport.matchesinfo.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Data class that represents Match Information
 */
@Parcelize
data class MatchDetails(
    @field:SerializedName("Team_A") val firstTeamName: String,
    @field:SerializedName("Team_B") val secondTeamName: String,
    @field:SerializedName("Score") val score: String,
    @field:SerializedName("link_A") val firstTeamImg: String,
    @field:SerializedName("link_B") val secondTeamImg: String,
    @field:SerializedName("Date") val matchDate: String
) : Parcelable
