package com.jawad.cryptocurrencyapp.presentation.coins.view_model

import androidx.lifecycle.viewModelScope
import com.jawad.cryptocurrencyapp.domain.model.Coin
import com.jawad.cryptocurrencyapp.domain.use_case.CoinsUseCase
import com.jawad.cryptocurrencyapp.domain.util.Resource
import com.jawad.cryptocurrencyapp.presentation.base.BaseViewModel
import com.jawad.cryptocurrencyapp.presentation.coins.adapter.CoinListingViewModel
import com.jawad.cryptocurrencyapp.presentation.base.adapter.ItemViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor(private val coinsUseCase: CoinsUseCase) : BaseViewModel() {

    private var _navigateToCoinDetail = MutableSharedFlow<String>()
    val navigateToCoinDetail = _navigateToCoinDetail.asSharedFlow()

    private var _progressStatus = MutableStateFlow(false)
    val getProgressStatus = _progressStatus.asStateFlow()

    private var _message = MutableStateFlow("")
    val getMessage = _message.asStateFlow()

    private var _coins = MutableStateFlow<List<Coin>>(emptyList())
    val getCoins = _coins.asStateFlow()

    private val _coinItemViewData = MutableStateFlow<List<ItemViewModel>>(emptyList())
    val getCoinItemViewData = _coinItemViewData.asStateFlow()


    init {
        getCoins()
    }

    private fun getCoins() {
        coinsUseCase().onEach {
            when (it) {
                is Resource.Error -> {
                    _progressStatus.emit(false)
                    _message.emit(it.message ?: "An error occurred please try again later.")
                }
                is Resource.Loading ->
                    _progressStatus.emit(true)

                is Resource.Success -> {
                    _progressStatus.emit(false)
                    _coins.emit(it.data ?: emptyList())
                    _coinItemViewData.emit(createViewData(it.data ?: emptyList()))
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun createViewData(coinList: List<Coin>): List<ItemViewModel> {
        val viewData = mutableListOf<ItemViewModel>()
        coinList.forEach { coin ->
            val item =
                CoinListingViewModel(
                    "${coin.rank}. ${coin.name} (${coin.symbol})",
                    coin.is_active
                ) {
                    viewModelScope.launch {
                        _navigateToCoinDetail.emit(coin.id)
                    }
                }
            viewData.add(item)
        }
        return viewData
    }


    companion object {
        const val LISTING_ITEM = 1
    }
}