package com.sport.matchesinfo.viewmodels

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.sport.matchesinfo.MainCoroutineRule
import com.sport.matchesinfo.api.Webservice
import com.sport.matchesinfo.data.MatchesListRepository
import com.sport.matchesinfo.data.local.AppDatabase
import com.sport.matchesinfo.data.local.MatchDetailsDao
import com.sport.matchesinfo.data.local.MockNetworkClient
import com.sport.matchesinfo.runBlockingTest
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner


/**
 * Test class for the viewModel
 */
@RunWith(MockitoJUnitRunner::class)
class MatchesListViewModelTest : TestCase() {
    lateinit var dao: MatchDetailsDao

    @Mock
    lateinit var webservice: Webservice

    var mockNetworkClient = MockNetworkClient()
    lateinit var repository: MatchesListRepository
    private lateinit var appDatabase: AppDatabase
    private lateinit var viewModel: MatchesListViewModel
    private val coroutineRule = MainCoroutineRule()

    @Before
    public override fun setUp() {
        super.setUp()
        webservice = mock(Webservice::class.java)
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dao = appDatabase.matchDetailsDao()
        repository = MatchesListRepository(webservice, mockNetworkClient, dao)
        viewModel = MatchesListViewModel(repository)
    }

    @Test
    fun testFetchMovies() =  coroutineRule.runBlockingTest {
        mockNetworkClient.mockResponseFilePath = "matchesList.json"

        viewModel.fetchMovies(true)

        Assert.assertTrue(dao.getAll()?.size!! > 1)
    }
}