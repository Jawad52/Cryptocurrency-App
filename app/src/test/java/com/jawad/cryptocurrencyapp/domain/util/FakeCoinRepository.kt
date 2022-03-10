package com.jawad.cryptocurrencyapp.domain.util

import com.jawad.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.jawad.cryptocurrencyapp.data.remote.dto.CoinDto
import com.jawad.cryptocurrencyapp.domain.repository.CoinRepository

class FakeCoinRepository : CoinRepository {
    override suspend fun getCoinList(): List<CoinDto> {
        return emptyList()
    }

    override suspend fun getCoinDetails(coinId: String): CoinDetailDto {
        return Constant.bitCoinDetailDto
    }
}