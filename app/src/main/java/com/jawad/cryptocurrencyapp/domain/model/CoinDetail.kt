package com.jawad.cryptocurrencyapp.domain.model

import com.jawad.cryptocurrencyapp.data.remote.dto.Team

data class CoinDetail(
    val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tags: String,
    val team: List<Team>
)
