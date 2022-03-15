package com.jawad.cryptocurrencyapp.presentation.coin_details.adapter

import com.jawad.cryptocurrencyapp.R
import com.jawad.cryptocurrencyapp.data.remote.dto.Team
import com.jawad.cryptocurrencyapp.presentation.base.adapter.ItemViewModel
import com.jawad.cryptocurrencyapp.presentation.coins.view_model.CoinsViewModel

class TeamMemberViewModel(val team: Team) : ItemViewModel {

    override val layoutId: Int = R.layout.item_team_listing

    override val viewType: Int = CoinsViewModel.LISTING_ITEM
}