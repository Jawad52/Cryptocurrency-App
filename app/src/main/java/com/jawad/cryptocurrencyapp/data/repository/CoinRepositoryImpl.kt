package com.jawad.cryptocurrencyapp.data.repository

import com.jawad.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.jawad.cryptocurrencyapp.data.remote.dto.CoinDto
import com.jawad.cryptocurrencyapp.data.remote.service.ApiService
import com.jawad.cryptocurrencyapp.domain.repository.CoinRepository

class CoinRepositoryImpl(private val apiService: ApiService) : CoinRepository {
    override suspend fun getCoinList(): List<CoinDto> {
        return apiService.getCoinList()
    }

    override suspend fun getCoinDetails(coinId: String): CoinDetailDto {
        return apiService.getCoinById(coinId)
    }
}