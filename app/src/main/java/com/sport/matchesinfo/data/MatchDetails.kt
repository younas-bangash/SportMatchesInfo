package com.sport.matchesinfo.data

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Data class that represents Match Information
 */
@Entity
@Parcelize
data class MatchDetails(
    @NonNull @PrimaryKey(autoGenerate = true) val id: Int,
    @field:SerializedName("Team_A") val firstTeamName: String,
    @field:SerializedName("Team_B") val secondTeamName: String,
    @field:SerializedName("Score") val score: String,
    @field:SerializedName("link_A") val firstTeamImg: String,
    @field:SerializedName("link_B") val secondTeamImg: String,
    @field:SerializedName("Date") val matchDate: String
) : Parcelable
