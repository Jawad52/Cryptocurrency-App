package com.jawad.cryptocurrencyapp.presentation.coin_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.jawad.cryptocurrencyapp.databinding.FragmentCoinDetailBinding
import com.jawad.cryptocurrencyapp.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CoinDetailFragment : BaseFragment() {

    private val coinDetailViewModel: CoinDetailViewModel by viewModels()
    private lateinit var binding: FragmentCoinDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoinDetailBinding.inflate(layoutInflater).apply {
            coinDetailsViewModelData = coinDetailViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }
}