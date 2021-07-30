package com.sport.matchesinfo.data.local

import androidx.room.Room
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.sport.matchesinfo.data.MatchDetails
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test class for the MatchDetailsDao
 */
@RunWith(AndroidJUnit4::class)
class MatchDetailsDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var matchDetailsDao: MatchDetailsDao

    private val matchA = MatchDetails(
        id = 1,
        firstTeamName = "firstTeamName1",
        secondTeamName = "secondTeamName1",
        firstTeamImg = "linkA",
        secondTeamImg = "linkB",
        score = "2-0",
        matchDate = "June 2021 Tuesday 23:00"
    )

    private val matchB = MatchDetails(
        id = 2,
        firstTeamName = "firstTeamName2",
        secondTeamName = "secondTeamName2",
        firstTeamImg = "linkA",
        secondTeamImg = "linkB",
        score = "2-0",
        matchDate = "June 2021 Tuesday 23:00"
    )

    private val matchC = MatchDetails(
        id = 3,
        firstTeamName = "firstTeamName3",
        secondTeamName = "secondTeamName3",
        firstTeamImg = "linkA",
        secondTeamImg = "linkB",
        score = "2-0",
        matchDate = "June 2021 Tuesday 23:00"
    )

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        matchDetailsDao = database.matchDetailsDao()
        matchDetailsDao.insertAll(listOf(matchA, matchB, matchC))
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGetAll() = runBlocking {
        val list = matchDetailsDao.getAll()
        assertThat(list?.size, equalTo(3))

        assertThat(list?.get(0), equalTo(matchA))
        assertThat(list?.get(1), equalTo(matchB))
        assertThat(list?.get(2), equalTo(matchC))
    }

    @Test
    fun testDelete() = runBlocking {
        matchDetailsDao.deleteRow(matchA.firstTeamName, matchA.secondTeamName)

        val list = matchDetailsDao.getAll()

        assertThat(list?.size, equalTo(2))
    }

    @After
    fun tearDown() {
        database.close()
    }
}