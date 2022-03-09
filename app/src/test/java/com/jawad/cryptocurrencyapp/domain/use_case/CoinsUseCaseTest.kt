package com.jawad.cryptocurrencyapp.domain.use_case

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
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
    fun `Should give error message when request for coin list`() = runTest(coroutineDispatcher) {
        coinsUseCase.getCoinList().test {
            awaitItem()
            val result = awaitItem()
            assertThat(result).isNotNull()
            assertThat(result).isInstanceOf(Resource.Error::class.java)
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