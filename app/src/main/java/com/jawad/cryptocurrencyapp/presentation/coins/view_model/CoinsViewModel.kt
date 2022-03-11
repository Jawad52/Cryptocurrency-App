package com.jawad.cryptocurrencyapp.presentation.coins.view_model

import androidx.lifecycle.viewModelScope
import com.jawad.cryptocurrencyapp.domain.use_case.CoinsUseCase
import com.jawad.cryptocurrencyapp.domain.util.Resource
import com.jawad.cryptocurrencyapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor(private val coinsUseCase: CoinsUseCase) : BaseViewModel() {

    private var _coinListState = MutableStateFlow(CoinListState())
    val getCoinListState = _coinListState.asSharedFlow()

    init {
        getCoins()
    }

    private fun getCoins() {
        coinsUseCase().onEach {
            when (it) {
                is Resource.Error -> {
                    _coinListState.value = CoinListState(
                        isLoading = false,
                        error = it.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> _coinListState.value = CoinListState(isLoading = true)
                is Resource.Success -> {
                    _coinListState.value = CoinListState(
                        isLoading = false,
                        coins = it.data ?: emptyList()
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}