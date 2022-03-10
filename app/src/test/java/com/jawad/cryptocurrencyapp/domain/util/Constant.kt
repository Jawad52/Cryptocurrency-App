package com.jawad.cryptocurrencyapp.domain.util

import com.jawad.cryptocurrencyapp.data.remote.dto.CoinDto
import com.jawad.cryptocurrencyapp.domain.model.Coin

class Constant {
    companion object {
        val bitCoin = Coin(
            id = "btc-bitcoin",
            is_active = true,
            name = "Bitcoin",
            rank = 1,
            symbol = "BTC",
        )

        val coins = listOf(
            CoinDto(
                id = "btc-bitcoin",
                is_active = true,
                is_new = false,
                name = "Bitcoin",
                rank = 1,
                symbol = "BTC",
                type = "coin"
            ),
            CoinDto(
                id = "algo-algorand",
                is_active = true,
                is_new = false,
                name = "Algorand",
                rank = 32,
                symbol = "ALGO",
                type = "coin"
            ),
            CoinDto(
                id = "aave-new",
                is_active = true,
                is_new = false,
                name = "Aave",
                rank = 60,
                symbol = "AAVE",
                type = "token"
            ),
        )
    }
}