package com.jawad.cryptocurrencyapp.domain.use_case

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.jawad.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.jawad.cryptocurrencyapp.domain.model.CoinDetail
import com.jawad.cryptocurrencyapp.util.Constant
import com.jawad.cryptocurrencyapp.domain.util.FakeCoinRepository
import com.jawad.cryptocurrencyapp.domain.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CoinsDetailUseCaseTest {
    private val coroutineDispatcher = StandardTestDispatcher()

    private val coinRepository = FakeCoinRepository()
    lateinit var coinDetailUseCase: CoinDetailUseCase
    private val errorMessage = "Coin id can't be null"
    private val coinId = "btc-bitcoin"

    @Before
    fun setUp() {
        coinDetailUseCase = CoinDetailUseCase(coroutineDispatcher, coinRepository)
    }

    @Test
    fun `Should fail when null coin id is passed return appropriate error message`() =
        runTest(coroutineDispatcher) {
            coinDetailUseCase(null).test {
                val result = awaitItem()
                assertThat(result).isNotNull()
                assertThat(result).isInstanceOf(Resource.Error::class.java)
                assertThat(result.message).isEqualTo(errorMessage)
                awaitComplete()
            }
        }

    @Test
    fun `Should fail when coin id is passed return different coin details instance`() =
        runTest(coroutineDispatcher) {
            coinDetailUseCase(coinId).test {
                awaitItem()
                val result = awaitItem()
                assertThat(result).isNotNull()
                assertThat(result).isInstanceOf(Resource.Success::class.java)
                assertThat(result.data).isNotInstanceOf(CoinDetailDto::class.java)
                awaitComplete()
            }
        }

    @Test
    fun `Should success when coin id is passed return proper coin details instance`() =
        runTest(coroutineDispatcher) {
            coinDetailUseCase(coinId).test {
                awaitItem()
                val result = awaitItem()
                assertThat(result).isNotNull()
                assertThat(result).isInstanceOf(Resource.Success::class.java)
                assertThat(result.data).isInstanceOf(CoinDetail::class.java)
                awaitComplete()
            }
        }

    @Test
    fun `Should success when the coin id passed and returns same coin id details`() =
        runTest(coroutineDispatcher) {
            coinDetailUseCase(coinId).test {
                awaitItem()
                val result = awaitItem()
                assertThat(result).isNotNull()
                assertThat(result.data).isEqualTo(Constant.bitCoinDetails)
                awaitComplete()
            }
        }
}