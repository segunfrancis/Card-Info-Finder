package com.segunfrancis.cardinfofinder.presentation.ui.keyboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.segunfrancis.cardinfofinder.databinding.EnterCardNumberFragmentBinding
import com.segunfrancis.cardinfofinder.presentation.ui.base.BaseFragment
import com.segunfrancis.cardinfofinder.presentation.ui.result.ResultFragment
import com.segunfrancis.cardinfofinder.presentation.util.Result.Success
import com.segunfrancis.cardinfofinder.presentation.util.Result.Error
import com.segunfrancis.cardinfofinder.presentation.util.Result.Loading
import com.segunfrancis.cardinfofinder.presentation.util.circularProgress
import com.segunfrancis.cardinfofinder.presentation.util.enableState
import com.segunfrancis.cardinfofinder.presentation.util.showMessage
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class EnterCardNumberFragment : BaseFragment<EnterCardNumberViewModel>() {

    private var _binding: EnterCardNumberFragmentBinding? = null
    private val binding get() = _binding!!

    override val viewModel: EnterCardNumberViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = EnterCardNumberFragmentBinding.inflate(layoutInflater)
        binding.checkCardButton.enableState(false)
        binding.cardNumberEditText.addTextChangedListener {
            it?.let {
                binding.checkCardButton.enableState(it.length >= 7)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.setImageDrawable(circularProgress(requireContext()))
    }

    override fun setClickListeners() {
        binding.apply {
            checkCardButton.setOnClickListener { viewModel.getCardInfo(cardNumberEditText.text.toString()) }
        }
    }

    override fun observeData() {
        viewModel.cardResponse.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Loading -> {
                    binding.progressBar.isVisible = true
                    binding.checkCardButton.enableState(false)
                }
                is Error -> {
                    binding.progressBar.isVisible = false
                    binding.checkCardButton.enableState(true)
                    binding.root.showMessage(getString(result.formattedErrorMessage))
                    Timber.e(result.error)
                }
                is Success -> {
                    ResultFragment(result.data) {
                        //navigateBack()
                    }.show(parentFragmentManager, "result_fragment")
                    binding.progressBar.isVisible = false
                    binding.checkCardButton.enableState(true)
                    Timber.d(result.data.toString())
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
