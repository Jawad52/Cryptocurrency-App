package com.jawad.cryptocurrencyapp.data.remote.service

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class ApiServiceTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: ApiService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Test
    fun `should fail when get coin list API request when getCoinList() returns with failure result`() {
        runBlocking {
            enqueueResponse(coinFileName)
            val resultResponse = service.getCoinList()
            val request = mockWebServer.takeRequest()
            assertThat(resultResponse).isNotNull()
            assertThat(request.path).isNotEqualTo("sample/test")
        }
    }

    @Test
    fun `should pass when get coin list API request when getCoinList() returns with success result`() {
        runBlocking {
            enqueueResponse(coinFileName)
            val resultResponse = service.getCoinList()
            val request = mockWebServer.takeRequest()
            assertThat(resultResponse).isNotNull()
            assertThat(request.path).isEqualTo(COINS_PATH)
        }
    }

    @Test
    fun `should get fail result's when getCoinList returns inappropriate data size`() {
        val coinListSize = 15
        runBlocking {
            enqueueResponse(coinFileName)
            val resultResponse = service.getCoinList()
            assertThat(resultResponse.size).isNotEqualTo(coinListSize)
        }
    }

    @Test
    fun `Should get getCoinList success returns with success with appropriate data size`() {
        val currencyListSize = 4
        runBlocking {
            enqueueResponse(coinFileName)
            val resultResponse = service.getCoinList()
            assertThat(resultResponse.size).isEqualTo(currencyListSize)
        }
    }

    @Test
    fun `should fail when different coin id is passed to getCoinById`(){
        val coinId = "algo-algorand"
        runBlocking {
            enqueueResponse(coinDetailFileName)
            val resultResponse = service.getCoinById(coinId)
            assertThat(resultResponse).isNotNull()
            assertThat(resultResponse.id).isNotEqualTo(coinId)
        }
    }

    @Test
    fun `should pass when proper coin id is passed to getCoinById`(){
        val coinId = "btc-bitcoin"
        runBlocking {
            enqueueResponse(coinDetailFileName)
            val resultResponse = service.getCoinById(coinId)
            assertThat(resultResponse).isNotNull()
            assertThat(resultResponse.id).isEqualTo(coinId)
        }
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader!!
            .getResourceAsStream("api-response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
            mockResponse.setBody(
                source.readString(Charsets.UTF_8)
            )
        )
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    companion object {
        private val coinFileName = "coins.json"
        private val coinDetailFileName = "coin_detail.json"
        private const val COINS_PATH = "/v1/coins"

    }
}