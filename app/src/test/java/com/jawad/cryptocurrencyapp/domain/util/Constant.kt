package com.jawad.cryptocurrencyapp.domain.util

import com.google.gson.Gson
import com.jawad.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.jawad.cryptocurrencyapp.data.remote.dto.CoinDto
import com.jawad.cryptocurrencyapp.data.remote.dto.toCoinDetail
import com.jawad.cryptocurrencyapp.domain.model.Coin

class Constant {
    companion object {
        var gson = Gson()

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

        val coinDetailDtoJSON = "{\n" +
                "  \"id\": \"btc-bitcoin\",\n" +
                "  \"name\": \"Bitcoin\",\n" +
                "  \"symbol\": \"BTC\",\n" +
                "  \"rank\": 1,\n" +
                "  \"is_new\": false,\n" +
                "  \"is_active\": true,\n" +
                "  \"type\": \"coin\",\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": \"segwit\",\n" +
                "      \"name\": \"Segwit\",\n" +
                "      \"coin_counter\": 10,\n" +
                "      \"ico_counter\": 0\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"cryptocurrency\",\n" +
                "      \"name\": \"Cryptocurrency\",\n" +
                "      \"coin_counter\": 783,\n" +
                "      \"ico_counter\": 40\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"proof-of-work\",\n" +
                "      \"name\": \"Proof Of Work\",\n" +
                "      \"coin_counter\": 413,\n" +
                "      \"ico_counter\": 14\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"payments\",\n" +
                "      \"name\": \"Payments\",\n" +
                "      \"coin_counter\": 171,\n" +
                "      \"ico_counter\": 39\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"sha256\",\n" +
                "      \"name\": \"Sha256\",\n" +
                "      \"coin_counter\": 42,\n" +
                "      \"ico_counter\": 1\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"mining\",\n" +
                "      \"name\": \"Mining\",\n" +
                "      \"coin_counter\": 275,\n" +
                "      \"ico_counter\": 18\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"lightning-network\",\n" +
                "      \"name\": \"Lightning Network\",\n" +
                "      \"coin_counter\": 6,\n" +
                "      \"ico_counter\": 0\n" +
                "    }\n" +
                "  ],\n" +
                "  \"team\": [\n" +
                "    {\n" +
                "      \"id\": \"satoshi-nakamoto\",\n" +
                "      \"name\": \"Satoshi Nakamoto\",\n" +
                "      \"position\": \"Founder\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"wladimir-j-van-der-laan\",\n" +
                "      \"name\": \"Wladimir J. van der Laan\",\n" +
                "      \"position\": \"Blockchain Developer\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"jonas-schnelli\",\n" +
                "      \"name\": \"Jonas Schnelli\",\n" +
                "      \"position\": \"Blockchain Developer\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": \"marco-falke\",\n" +
                "      \"name\": \"Marco Falke\",\n" +
                "      \"position\": \"Blockchain Developer\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"description\": \"Bitcoin is a cryptocurrency and worldwide payment system. It is the first decentralized digital currency, as the system works without a central bank or single administrator.\",\n" +
                "  \"message\": \"\",\n" +
                "  \"open_source\": true,\n" +
                "  \"started_at\": \"2009-01-03T00:00:00Z\",\n" +
                "  \"development_status\": \"Working product\",\n" +
                "  \"hardware_wallet\": true,\n" +
                "  \"proof_type\": \"Proof of Work\",\n" +
                "  \"org_structure\": \"Decentralized\",\n" +
                "  \"hash_algorithm\": \"SHA256\",\n" +
                "  \"links\": {\n" +
                "    \"explorer\": [\n" +
                "      \"https://blockchair.com/bitcoin\",\n" +
                "      \"https://blockchain.com/explorer\",\n" +
                "      \"https://blockstream.info/\",\n" +
                "      \"https://live.blockcypher.com/btc/\",\n" +
                "      \"https://btc.cryptoid.info/btc/\"\n" +
                "    ],\n" +
                "    \"facebook\": [\n" +
                "      \"https://www.facebook.com/bitcoins/\"\n" +
                "    ],\n" +
                "    \"reddit\": [\n" +
                "      \"https://www.reddit.com/r/bitcoin\"\n" +
                "    ],\n" +
                "    \"source_code\": [\n" +
                "      \"https://github.com/bitcoin/bitcoin\"\n" +
                "    ],\n" +
                "    \"website\": [\n" +
                "      \"https://bitcoin.org/\"\n" +
                "    ],\n" +
                "    \"youtube\": [\n" +
                "      \"https://www.youtube.com/watch?v=Gc2en3nHxA4\\u0026\"\n" +
                "    ]\n" +
                "  },\n" +
                "  \"links_extended\": [\n" +
                "    {\n" +
                "      \"url\": \"https://bitcoin.org/en/blog\",\n" +
                "      \"type\": \"blog\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"https://blockchair.com/bitcoin\",\n" +
                "      \"type\": \"explorer\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"https://blockchain.com/explorer\",\n" +
                "      \"type\": \"explorer\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"https://blockstream.info/\",\n" +
                "      \"type\": \"explorer\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"https://live.blockcypher.com/btc/\",\n" +
                "      \"type\": \"explorer\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"https://btc.cryptoid.info/btc/\",\n" +
                "      \"type\": \"explorer\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"https://www.facebook.com/bitcoins/\",\n" +
                "      \"type\": \"facebook\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"https://bitcointalk.org\",\n" +
                "      \"type\": \"message_board\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"https://www.reddit.com/r/bitcoin\",\n" +
                "      \"type\": \"reddit\",\n" +
                "      \"stats\": {\n" +
                "        \"subscribers\": 3977947\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"https://github.com/bitcoin/bitcoin\",\n" +
                "      \"type\": \"source_code\",\n" +
                "      \"stats\": {\n" +
                "        \"contributors\": 1048,\n" +
                "        \"stars\": 62345\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"https://twitter.com/bitcoincoreorg\",\n" +
                "      \"type\": \"twitter\",\n" +
                "      \"stats\": {\n" +
                "        \"followers\": 153088\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"https://electrum.org/#download\",\n" +
                "      \"type\": \"wallet\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"https://bitcoin.org/\",\n" +
                "      \"type\": \"website\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"url\": \"https://www.youtube.com/watch?v=Gc2en3nHxA4\\u0026\",\n" +
                "      \"type\": \"youtube\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"whitepaper\": {\n" +
                "    \"link\": \"https://static.coinpaprika.com/storage/cdn/whitepapers/215.pdf\",\n" +
                "    \"thumbnail\": \"https://static.coinpaprika.com/storage/cdn/whitepapers/217.jpg\"\n" +
                "  },\n" +
                "  \"first_data_at\": \"2010-07-17T00:00:00Z\",\n" +
                "  \"last_data_at\": \"2022-03-09T08:20:00Z\"\n" +
                "}"
        val bitCoinDetailDto = gson.fromJson(coinDetailDtoJSON, CoinDetailDto::class.java)

        val bitCoinDetails = bitCoinDetailDto.toCoinDetail()
    }
}