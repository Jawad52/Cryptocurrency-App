package com.jawad.cryptocurrencyapp.presentation.coins

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.jawad.cryptocurrencyapp.databinding.FragmentCoinsBinding
import com.jawad.cryptocurrencyapp.presentation.base.BaseFragment
import com.jawad.cryptocurrencyapp.presentation.coins.view_model.CoinsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach

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
        lifecycleScope.launchWhenStarted {
            coinsViewModel.navigateToCoinDetail.collectLatest {
                Toast.makeText(requireContext(), "ID $it", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
}