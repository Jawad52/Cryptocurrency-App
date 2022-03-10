package com.jawad.cryptocurrencyapp.presentation.coins.view_model

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.jawad.cryptocurrencyapp.domain.use_case.CoinsUseCase
import com.jawad.cryptocurrencyapp.domain.util.Resource
import com.jawad.cryptocurrencyapp.util.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
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
import org.mockito.kotlin.whenever
import java.io.IOException
import java.lang.Exception

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CoinsViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private val testCoroutineDispatcher = StandardTestDispatcher()

    private val networkErrorMessage = "No internet available"

    lateinit var coinsViewModel: CoinsViewModel


    @Before
    fun setUp() {
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @Test
    fun `Verify the progress status when view model is initiated`() =
        runTest(testCoroutineDispatcher) {
            val coinsUseCase = mock<CoinsUseCase> {
                on { invoke() } doReturn flow {
                    emit(Resource.Loading())
                    emit(Resource.Success(Constant.coins))
                }
            }
            coinsViewModel = CoinsViewModel(coinsUseCase)
            coinsViewModel.getProgressStatus.test {
                assertThat(awaitItem()).isEqualTo(true)
                assertThat(awaitItem()).isEqualTo(false)
            }
        }

    @Test
    fun `Should give network error message`() = runTest(testCoroutineDispatcher) {
        val coinsUseCaseForError = mock<CoinsUseCase> {
            on { invoke() } doReturn flow {
                emit(Resource.Error(networkErrorMessage))
            }
        }
        val coinsViewModel = CoinsViewModel(coinsUseCaseForError)
        coinsViewModel.getMessage.test {
            val result = awaitItem()
            assertThat(result).isEqualTo(networkErrorMessage)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}