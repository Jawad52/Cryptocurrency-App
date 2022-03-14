package com.jawad.cryptocurrencyapp.presentation.coins

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.jawad.cryptocurrencyapp.databinding.FragmentCoinsBinding
import com.jawad.cryptocurrencyapp.presentation.base.BaseFragment
import com.jawad.cryptocurrencyapp.presentation.coins.view_model.CoinsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CoinsFragment : BaseFragment() {

    private val coinsViewModel: CoinsViewModel by viewModels()
    lateinit var binding: FragmentCoinsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoinsBinding.inflate(layoutInflater).apply {
            coinsViewModelData = coinsViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            coinsViewModel.navigateToCoinDetail.collect {
                val action = CoinsFragmentDirections.actionCoinsFragmentToCoinDetailFragment(it)
                findNavController().navigate(action)
            }
        }
        return binding.root
    }
}