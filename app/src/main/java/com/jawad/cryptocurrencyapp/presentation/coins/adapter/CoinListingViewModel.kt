package com.jawad.cryptocurrencyapp.presentation.coins.adapter

import com.jawad.cryptocurrencyapp.R
import com.jawad.cryptocurrencyapp.presentation.base.adapter.ItemViewModel
import com.jawad.cryptocurrencyapp.presentation.coins.view_model.CoinsViewModel

class CoinListingViewModel(
    val coinText: String,
    val isActive: Boolean,
    private val onItemClick: () -> Unit
) : ItemViewModel {

    override val layoutId: Int = R.layout.item_coin_listing

    override val viewType: Int = CoinsViewModel.LISTING_ITEM

    fun onClick() {
        onItemClick()
    }
}