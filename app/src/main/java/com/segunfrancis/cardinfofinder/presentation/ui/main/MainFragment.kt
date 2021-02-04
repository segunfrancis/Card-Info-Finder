package com.segunfrancis.cardinfofinder.presentation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.segunfrancis.cardinfofinder.databinding.MainFragmentBinding
import com.segunfrancis.cardinfofinder.presentation.ui.base.BaseFragment


class MainFragment : BaseFragment<ViewModel>() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun setClickListeners() {
        binding.apply {
            card1.setOnClickListener { launchFragment(MainFragmentDirections.actionMainFragmentToEnterCardNumberFragment()) }
            card2.setOnClickListener { launchFragment(MainFragmentDirections.actionMainFragmentToOCRFragment()) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override val viewModel: ViewModel?
        get() = null
}