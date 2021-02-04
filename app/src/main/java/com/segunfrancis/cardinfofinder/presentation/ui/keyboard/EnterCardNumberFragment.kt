package com.segunfrancis.cardinfofinder.presentation.ui.keyboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.segunfrancis.cardinfofinder.databinding.EnterCardNumberFragmentBinding
import com.segunfrancis.cardinfofinder.domain.CardInfoEntity
import com.segunfrancis.cardinfofinder.presentation.ui.base.BaseFragment
import com.segunfrancis.cardinfofinder.presentation.util.AppConstants.DEFAULT_TEXT
import com.segunfrancis.cardinfofinder.presentation.util.Result.Success
import com.segunfrancis.cardinfofinder.presentation.util.Result.Error
import com.segunfrancis.cardinfofinder.presentation.util.Result.Loading
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
                }
                is Error -> {
                    binding.progressBar.isVisible = false
                    binding.root.showMessage(getString(result.formattedErrorMessage))
                    Timber.e(result.error)
                }
                is Success -> {
                    populateData(result.data)
                    binding.progressBar.isVisible = false
                    Timber.d(result.data.toString())
                }
            }
        }
    }

    private fun populateData(cardInfo: CardInfoEntity) {
        with(binding) {
            textScheme.text = cardInfo.scheme ?: DEFAULT_TEXT
            textBank.text = cardInfo.bank.name ?: DEFAULT_TEXT
            textCardNumberLength.text = cardInfo.number?.length?.toString() ?: DEFAULT_TEXT
            textCountry.text = cardInfo.country?.name ?: DEFAULT_TEXT
            textPrepaid.text = cardInfo.prepaid?.toString() ?: DEFAULT_TEXT
            textType.text = cardInfo.type ?: DEFAULT_TEXT
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
