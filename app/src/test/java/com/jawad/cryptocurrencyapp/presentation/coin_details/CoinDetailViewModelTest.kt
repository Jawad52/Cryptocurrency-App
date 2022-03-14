package com.jawad.cryptocurrencyapp.presentation.coin_details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.jawad.cryptocurrencyapp.domain.use_case.CoinDetailUseCase
import com.jawad.cryptocurrencyapp.domain.util.Resource
import com.jawad.cryptocurrencyapp.util.Constant
import com.jawad.cryptocurrencyapp.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CoinDetailViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private val testCoroutineDispatcher = StandardTestDispatcher()

    private val coinId = "btc-bitcoin"
    private val savedStateHandle = SavedStateHandle().apply {
        set(Constants.PARAM_COIN_ID, coinId)
    }

    private val networkErrorMessage = "No internet available"
    private val invalidCoinIdErrorMessage = "Coin id can't be null or empty"

    private lateinit var coinDetailViewModel: CoinDetailViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @Test
    fun `Verify the progress status for the error response`() =
        runTest(testCoroutineDispatcher) {
            val coinDetailUseCase = mock<CoinDetailUseCase> {
                on { invoke(coinId) } doReturn flow {
                    emit(Resource.Loading())
                    emit(Resource.Error(""))
                }
            }
            coinDetailViewModel = CoinDetailViewModel(coinDetailUseCase, savedStateHandle)
            coinDetailViewModel.getProgressStatus.test {
                assertThat(awaitItem()).isEqualTo(false)
                assertThat(awaitItem()).isEqualTo(true)
                assertThat(awaitItem()).isEqualTo(false)
            }
        }

    @Test
    fun `Verify the progress status when view model is initiated when valid coin id is passed`() =
        runTest(testCoroutineDispatcher) {
            val coinId = "btc-bitcoin"
            val coinsUseCase = mock<CoinDetailUseCase> {
                on { invoke(coinId) } doReturn flow {
                    emit(Resource.Loading())
                    emit(Resource.Success(Constant.bitCoinDetails))
                }
            }
            coinDetailViewModel = CoinDetailViewModel(coinsUseCase, savedStateHandle)
            coinDetailViewModel.getProgressStatus.test {
                assertThat(awaitItem()).isEqualTo(false)
                assertThat(awaitItem()).isEqualTo(true)
                assertThat(awaitItem()).isEqualTo(false)
            }
        }

    @Test
    fun `Should fail when coin id is empty return invalid coin id`() =
        runTest(testCoroutineDispatcher) {
            val emptyCoinId = ""
            val savedStateHandle = SavedStateHandle().apply {
                set(Constants.PARAM_COIN_ID, emptyCoinId)
            }

            val coinsUseCase = mock<CoinDetailUseCase>() {
                on { invoke(emptyCoinId) } doReturn flow {
                    emit(
                        Resource.Error(
                            invalidCoinIdErrorMessage
                        )
                    )
                }
            }
            coinDetailViewModel = CoinDetailViewModel(coinsUseCase, savedStateHandle)
            coinDetailViewModel.getMessage.test {
                awaitItem()
                val result = awaitItem()
                assertThat(result).isNotNull()
                assertThat(result).isEqualTo(invalidCoinIdErrorMessage)
            }
        }

    @Test
    fun `Should fail and show network error message when get coin detail is called`() =
        runTest(testCoroutineDispatcher) {
            val coinsUseCase = mock<CoinDetailUseCase> {
                on { invoke(coinId) } doReturn flow {
                    emit(Resource.Error(networkErrorMessage))
                }
            }
            coinDetailViewModel = CoinDetailViewModel(coinsUseCase, savedStateHandle)
            coinDetailViewModel.getMessage.test {
                awaitItem()
                val result = awaitItem()
                assertThat(result).isEqualTo(networkErrorMessage)
            }
        }

    @Test
    fun `Should success when coin id passed return coin id detail`() =
        runTest(testCoroutineDispatcher) {
            val coinsUseCase = mock<CoinDetailUseCase> {
                on { invoke(coinId) } doReturn flow {
                    emit(Resource.Loading())
                    emit(Resource.Success(Constant.bitCoinDetails))
                }
            }
            coinDetailViewModel = CoinDetailViewModel(coinsUseCase, savedStateHandle)

            coinDetailViewModel.getCoinDetail.test {
                awaitItem()
                val result = awaitItem()
                assertThat(result).isNotNull()
                assertThat(result!!.coinId).isEqualTo(coinId)
            }
        }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}