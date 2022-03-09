package com.jawad.cryptocurrencyapp.domain.repository

import com.jawad.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.jawad.cryptocurrencyapp.data.remote.dto.CoinDto

interface CoinRepository {
    suspend fun getCoinList(): List<CoinDto>
    suspend fun getCoinDetails(coinId: String): CoinDetailDto
}