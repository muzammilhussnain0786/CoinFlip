package com.coinflip.takehome.network

import com.coinflip.takehome.data.remote.CoinGeckoService
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import java.net.HttpURLConnection

class NetworkTest {

    private lateinit var network: MockNetworkModule
    private lateinit var json: Json
    private lateinit var retrofit: Retrofit
    private lateinit var service: CoinGeckoService
    private val mockWebServer: MockWebServer = MockWebServer()
    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        mockWebServer.start()
        network = MockNetworkModule(mockWebServer)
        json = network.provideKotlinSerializationJson()
        retrofit = network.provideRetrofitClient(json)
        service = network.provideCoinGeckoService(retrofit)
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return if(request.path?.contains("page=1") == true){
                    MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody(json.encodeToString(FakeData.listOfEntities))
                } else {
                    MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody("[]")
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown(){
        mockWebServer.shutdown()
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testGetCoinsApi() = runTest(testDispatcher) {
        val response = service.getCoins()
        assertEquals(FakeData.listOfEntities.size, response.size)
        assertEquals(FakeData.listOfEntities[0].id, response[0].id)
    }
}