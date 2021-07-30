package com.sport.matchesinfo.viewmodels

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.sport.matchesinfo.api.Webservice
import com.sport.matchesinfo.data.MatchesListRepository
import com.sport.matchesinfo.data.local.AppDatabase
import com.sport.matchesinfo.data.local.MatchDetailsDao
import com.sport.matchesinfo.data.local.MockNetworkClient
import junit.framework.TestCase
import org.junit.Before
import javax.inject.Inject

/**
 * Test class for the viewModel
 */

class MatchesListViewModelTest : TestCase() {
    @Inject
    lateinit var webservice: Webservice

    @Inject
    lateinit var dao: MatchDetailsDao
    var mockNetworkClient = MockNetworkClient()
    lateinit var repository: MatchesListRepository
    private lateinit var appDatabase: AppDatabase
    private lateinit var viewModel: MatchesListViewModel

    @Before
    public override fun setUp() {
        super.setUp()
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        repository = MatchesListRepository(webservice, mockNetworkClient, dao)
        viewModel = MatchesListViewModel(repository)
    }

    fun testFetchMovies() {
        viewModel.fetchMovies(true)
    }
}