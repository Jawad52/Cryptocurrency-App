package com.jawad.cryptocurrencyapp.domain.use_case

import com.jawad.cryptocurrencyapp.domain.util.FakeCoinRepository
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CoinsUseCaseTest {
    private val coinRepository = FakeCoinRepository()
    lateinit var coinsUseCase: CoinsUseCase

    @Before
    fun setUp() {
        coinsUseCase = CoinsUseCase(coinRepository)
    }

    //Check for empty list and null
    //Check for data type
}