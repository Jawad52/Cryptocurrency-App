package com.jawad.cryptocurrencyapp.domain.use_case

import com.jawad.cryptocurrencyapp.data.remote.dto.toCoin
import com.jawad.cryptocurrencyapp.domain.model.Coin
import com.jawad.cryptocurrencyapp.domain.repository.CoinRepository
import com.jawad.cryptocurrencyapp.domain.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException

class CoinsUseCase(
    private val dispatcher: CoroutineDispatcher,
    private val coinsRepository: CoinRepository
) {

    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        emit(Resource.Loading())
        val response = coinsRepository.getCoinList().map { it.toCoin() }
        if (response.isNotEmpty()) {
            emit(Resource.Success(response))
        } else {
            emit(Resource.Error("No data available"))
        }
    }.catch {
        when (it) {
            is IOException -> {
                emit(Resource.Error("Unable to reach server, check your internet"))
            }
            else -> {
                it.localizedMessage?.let { message ->
                    emit(Resource.Error(message))
                } ?: run {
                    emit(Resource.Error("Sorry something went wrong, please try again"))
                }
            }
        }
    }.flowOn(dispatcher)
}