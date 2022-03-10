package com.jawad.cryptocurrencyapp.domain.use_case

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.jawad.cryptocurrencyapp.data.remote.dto.CoinDto
import com.jawad.cryptocurrencyapp.domain.model.Coin
import com.jawad.cryptocurrencyapp.domain.util.Constant
import com.jawad.cryptocurrencyapp.domain.util.FakeCoinRepository
import com.jawad.cryptocurrencyapp.domain.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.io.IOException
import java.lang.Exception

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CoinsUseCaseTest {
    private val coroutineDispatcher = StandardTestDispatcher()

    private val coinRepository = FakeCoinRepository()
    lateinit var coinsUseCase: CoinsUseCase


    @Before
    fun setUp() {
        coinsUseCase = CoinsUseCase(coroutineDispatcher, coinRepository)
    }

    @Test
    fun `Should fail with no data available message when request for coin list`() =
        runTest(coroutineDispatcher) {
            val message = "No data available"
            coinsUseCase().test {
                awaitItem()
                val result = awaitItem()
                assertThat(result).isNotNull()
                assertThat(result).isInstanceOf(Resource.Error::class.java)
                assertThat(result.message).isEqualTo(message)
                awaitComplete()
            }
        }

    @Test
    fun `Should success when request for coin list with Resource_Success instance`() =
        runTest(coroutineDispatcher) {
            val repository = mock<FakeCoinRepository>()
            whenever(repository.getCoinList()) doReturn Constant.coins
            val useCase = CoinsUseCase(coroutineDispatcher, repository)
            useCase().test {
                awaitItem()
                val result = awaitItem()
                assertThat(result).isNotNull()
                assertThat(result.data!!.size).isNotEqualTo(0)
                assertThat(result).isInstanceOf(Resource.Success::class.java)
                awaitComplete()
            }
        }

    @Test
    fun `Verify that CoinDto instance is not returned when get coin list method is called`() =
        runTest(coroutineDispatcher) {
            val repository = mock<FakeCoinRepository>()
            whenever(repository.getCoinList()) doReturn Constant.coins
            val useCase = CoinsUseCase(coroutineDispatcher, repository)
            useCase().test {
                awaitItem()
                val result = awaitItem()
                assertThat(result).isNotNull()
                assertThat(result.data!![0]).isNotInstanceOf(CoinDto::class.java)
                awaitComplete()
            }
        }

    @Test
    fun `Verify that Coin instance is returned when get coin list method is called`() =
        runTest(coroutineDispatcher) {
            val repository = mock<FakeCoinRepository>()
            whenever(repository.getCoinList()) doReturn Constant.coins
            val useCase = CoinsUseCase(coroutineDispatcher, repository)
            useCase().test {
                awaitItem()
                val result = awaitItem()
                assertThat(result).isNotNull()
                assertThat(result.data!![0]).isInstanceOf(Coin::class.java)
                awaitComplete()
            }
        }

    @Test
    fun `Verify that first rank of coin list must Bit coin`() =
        runTest(coroutineDispatcher) {
            val repository = mock<FakeCoinRepository>()
            whenever(repository.getCoinList()) doReturn Constant.coins
            val useCase = CoinsUseCase(coroutineDispatcher, repository)
            useCase().test {
                awaitItem()
                val result = awaitItem()
                assertThat(result).isNotNull()
                assertThat(result.data!!.size).isNotEqualTo(0)
                assertThat(result.data!![0]).isEqualTo(Constant.bitCoin)
                awaitComplete()
            }
        }

    //check for exception
    //check for two times retry
    //check for empty list and null
    //check for list size
    //check for data type
    //check for wrong data value
    //check for correct data value
}