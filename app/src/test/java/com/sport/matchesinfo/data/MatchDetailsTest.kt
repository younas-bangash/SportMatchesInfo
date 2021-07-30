package com.sport.matchesinfo.data

import junit.framework.TestCase
import org.junit.Assert

class MatchDetailsTest : TestCase() {
    private lateinit var matchDetails: MatchDetails

    public override fun setUp() {
        super.setUp()
        matchDetails = MatchDetails(
            id = 0,
            firstTeamName = "firstTeamName",
            secondTeamName = "secondTeamName",
            firstTeamImg = "linkA",
            secondTeamImg = "linkB",
            score = "2-0",
            matchDate = "June 2021 Tuesday 23:00"
        )
    }

    fun testAttributes() {
        Assert.assertNotNull(matchDetails)
        Assert.assertEquals("firstTeamName", matchDetails.firstTeamName)
        Assert.assertEquals("secondTeamName", matchDetails.secondTeamName)
        Assert.assertEquals("linkA", matchDetails.firstTeamImg)
        Assert.assertEquals("linkB", matchDetails.secondTeamImg)
        Assert.assertEquals("2-0", matchDetails.score)
        Assert.assertEquals("June 2021 Tuesday 23:00", matchDetails.matchDate)
    }
}