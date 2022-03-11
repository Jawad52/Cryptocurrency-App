package com.jawad.cryptocurrencyapp.presentation.coins.view_model

import androidx.lifecycle.viewModelScope
import com.jawad.cryptocurrencyapp.domain.model.Coin
import com.jawad.cryptocurrencyapp.domain.use_case.CoinsUseCase
import com.jawad.cryptocurrencyapp.domain.util.Resource
import com.jawad.cryptocurrencyapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor(private val coinsUseCase: CoinsUseCase) : BaseViewModel() {

    private var _progressStatus = MutableStateFlow(false)
    val getProgressStatus = _progressStatus.asStateFlow()

    private var _message = MutableStateFlow("")
    val getMessage = _message.asStateFlow()

    private var _coins = MutableStateFlow<List<Coin>>(emptyList())
    val getCoins = _coins.asStateFlow()

    init {
        getCoins()
    }

    private fun getCoins() {
        coinsUseCase().onEach {
            when (it) {
                is Resource.Error -> {
                    _progressStatus.emit(false)
                    _message.emit(it.message!!)
                }
                is Resource.Loading ->
                    _progressStatus.emit(true)

                is Resource.Success -> {
                    _progressStatus.emit(false)
                    _coins.emit(it.data ?: emptyList())
                }
            }
        }.launchIn(viewModelScope)
    }
}