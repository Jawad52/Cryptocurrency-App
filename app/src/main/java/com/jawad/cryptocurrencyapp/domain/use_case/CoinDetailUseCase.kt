package com.jawad.cryptocurrencyapp.domain.use_case

import com.jawad.cryptocurrencyapp.data.remote.dto.toCoinDetail
import com.jawad.cryptocurrencyapp.domain.model.CoinDetail
import com.jawad.cryptocurrencyapp.domain.repository.CoinRepository
import com.jawad.cryptocurrencyapp.domain.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class CoinDetailUseCase @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val coinsRepository: CoinRepository
) {

    operator fun invoke(coinId: String?): Flow<Resource<CoinDetail>> = flow<Resource<CoinDetail>> {
        emit(Resource.Loading())
        coinId?.let {
            if (coinId.isNotEmpty()) {
                val response = coinsRepository.getCoinDetails(coinId).toCoinDetail()
                emit(Resource.Success(response))
            } else
                emit(Resource.Error("Coin id can't be empty"))
        } ?: run {
            emit(Resource.Error("Coin id can't be null"))
        }
    }.catch {
        when (it) {
            is IOException -> {
                emit(Resource.Error("Unable to reach server, check your internet"))
            }
            else -> {
                emit(Resource.Error(it.localizedMessage))
            }
        }
    }.flowOn(dispatcher)
}