package com.jawad.cryptocurrencyapp.presentation.coin_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.jawad.cryptocurrencyapp.data.remote.dto.Team
import com.jawad.cryptocurrencyapp.domain.model.Coin
import com.jawad.cryptocurrencyapp.domain.model.CoinDetail
import com.jawad.cryptocurrencyapp.domain.use_case.CoinDetailUseCase
import com.jawad.cryptocurrencyapp.domain.util.Resource
import com.jawad.cryptocurrencyapp.presentation.base.BaseViewModel
import com.jawad.cryptocurrencyapp.presentation.base.adapter.ItemViewModel
import com.jawad.cryptocurrencyapp.presentation.coin_details.adapter.TeamMemberViewModel
import com.jawad.cryptocurrencyapp.presentation.coins.adapter.CoinListingViewModel
import com.jawad.cryptocurrencyapp.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val coinDetailUseCase: CoinDetailUseCase,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private var _progressStatus = MutableStateFlow(false)
    val getProgressStatus = _progressStatus.asStateFlow()

    private var _message = MutableStateFlow("")
    val getMessage = _message.asStateFlow()

    private var _coinDetail = MutableStateFlow<CoinDetail?>(null)
    val getCoinDetail = _coinDetail.asStateFlow()

    private var _teams = MutableStateFlow<List<ItemViewModel>?>(null)
    val getTeams = _teams.asStateFlow()

    init {
        getCoinDetails()
    }

    private fun getCoinDetails() {
        val coinId = savedStateHandle.get<String>(Constants.PARAM_COIN_ID)
        coinDetailUseCase(coinId).onEach {
            when (it) {
                is Resource.Error -> {
                    _progressStatus.emit(false)
                    _message.emit(it.message ?: "An error occurred please try again later.")
                }
                is Resource.Loading ->
                    _progressStatus.emit(true)
                is Resource.Success -> {
                    _progressStatus.emit(false)
                    it.data?.let { coinDetail ->
                        _coinDetail.emit(coinDetail)
                        _teams.emit(createItemView(coinDetail.team))
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun createItemView(teams: List<Team>): List<ItemViewModel> {
        val itemViewModel = arrayListOf<ItemViewModel>()
        teams.forEach { team ->
            val teamViewHolder = TeamMemberViewModel(team)
            itemViewModel.add(teamViewHolder)
        }
        return itemViewModel
    }
}