package com.sport.matchesinfo.viewmodels

import androidx.lifecycle.ViewModel
import com.sport.matchesinfo.data.MatchDetails
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * Details screen ViewModel
 */
class MatchDetailsViewModel @Inject constructor() : ViewModel() {

    companion object {
        const val SERVER_DATE_TIME_FORMAT: String = "d MMM yyyy HH:mm"
        const val DISPLAY_TIME_FORMAT: String = "hh:mm a"
        const val DISPLAY_DATE_FORMAT: String = "d MMM yyyy"
    }

    lateinit var matchDetails: MatchDetails

    fun getFirstTeamScore(): String {
        return matchDetails.score.split("-")[0]
    }

    fun getSecondTeamScore(): String {
        return matchDetails.score.split("-")[1]
    }

    fun getMatchDate(): String {
        val date: Date? = getMatchDateTime()
        val timeFormatter = SimpleDateFormat(DISPLAY_DATE_FORMAT, Locale.ENGLISH)
        return timeFormatter.format(date)
    }

    fun getMatchTime(): String {
        val date: Date? = getMatchDateTime()
        val timeFormatter = SimpleDateFormat(DISPLAY_TIME_FORMAT, Locale.ENGLISH)
        return timeFormatter.format(date)
    }

    private fun getMatchDateTime(): Date? {
        val format = SimpleDateFormat(SERVER_DATE_TIME_FORMAT, Locale.ENGLISH)
        val date: Date? = format.parse(matchDetails.matchDate)
        return date
    }
}