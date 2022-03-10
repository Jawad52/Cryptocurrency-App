package com.jawad.cryptocurrencyapp.presentation.coins.view_model

import com.jawad.cryptocurrencyapp.domain.use_case.CoinsUseCase
import com.jawad.cryptocurrencyapp.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoinsViewModel @Inject constructor(useCase: CoinsUseCase) : BaseViewModel() {

    init {

    }

    fun getCoins() {

    }
}