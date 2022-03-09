package com.jawad.cryptocurrencyapp.domain.util

import com.jawad.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.jawad.cryptocurrencyapp.data.remote.dto.CoinDto
import com.jawad.cryptocurrencyapp.data.remote.dto.Links
import com.jawad.cryptocurrencyapp.data.remote.dto.Whitepaper
import com.jawad.cryptocurrencyapp.domain.repository.CoinRepository

class FakeCoinRepository : CoinRepository {
    override suspend fun getCoinList(): List<CoinDto> {
        return emptyList()
    }

    override suspend fun getCoinDetails(coinId: String): CoinDetailDto {
        return CoinDetailDto(
            description = "",
            development_status = "",
            first_data_at = "", hardware_wallet = false, hash_algorithm = "1", id = "",
            is_active = false,
            is_new = false,
            last_data_at = "",
            links = Links(
                explorer = emptyList(),
                facebook = emptyList(),
                reddit = emptyList(),
                source_code = emptyList(),
                website = emptyList(),
                youtube = emptyList()
            ),
            links_extended = emptyList(),
            message = "",
            name = "",
            open_source = false,
            org_structure = "",
            proof_type = "",
            rank = 0,
            started_at = "",
            symbol = "",
            tags = emptyList(),
            team = emptyList(),
            type = "",
            whitepaper = Whitepaper(link = "", thumbnail = "")
        )
    }
}