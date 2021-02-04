package com.segunfrancis.cardinfofinder.presentation.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

abstract class BaseFragment<VM: ViewModel?> : Fragment() {

    protected abstract val viewModel: VM?

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
        observeData()
    }

    open fun setClickListeners() {

    }

    open fun observeData() {

    }

    fun navigateBack() {
        findNavController().navigateUp()
    }

    fun launchFragment(directions: NavDirections) {
        findNavController().navigate(directions)
    }
}