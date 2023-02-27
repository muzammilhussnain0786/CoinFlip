package com.coinflip.takehome.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.coinflip.takehome.data.FakeData.listOfEntities
import com.coinflip.takehome.data.local.database.AppDatabase
import com.coinflip.takehome.data.local.database.CoinEntityDao
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CoinEntityDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: CoinEntityDao
    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dao = database.coinMarketEntityDao()

        dao.insertAll(listOfEntities)
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        database.close()
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testGetCoinsMarket() = runTest(testDispatcher) {
        val first = dao.getCoinsMarket().first()
        assertEquals("bitcoin", first[0].id)
        assertEquals("ethereum", first[1].id)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testGetCoin() = runTest(testDispatcher) {

        val coin = dao.getCoin("ethereum").first()
        assertEquals("ethereum", coin.id)
    }
}