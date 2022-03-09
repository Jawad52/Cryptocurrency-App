package com.jawad.cryptocurrencyapp.data.remote.service

import com.jawad.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.jawad.cryptocurrencyapp.data.remote.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("v1/coins")
    suspend fun getCoinList(): List<CoinDto>

    @GET("v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDto
}