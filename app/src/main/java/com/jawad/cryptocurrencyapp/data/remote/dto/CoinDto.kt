package com.jawad.cryptocurrencyapp.data.remote.dto

import com.jawad.cryptocurrencyapp.domain.model.Coin

data class CoinDto(
    val id: String,
    val is_active: Boolean,
    val is_new: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

fun CoinDto.toCoin() = Coin(
    id = this.id,
    is_active = this.is_active,
    symbol = this.symbol,
    name = this.name,
    rank = this.rank
)