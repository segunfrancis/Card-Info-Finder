package com.segunfrancis.cardinfofinder.presentation.ui.ocr

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.segunfrancis.cardinfofinder.databinding.OCRFragmentBinding
import com.segunfrancis.cardinfofinder.presentation.ui.base.BaseFragment
import com.segunfrancis.cardinfofinder.presentation.ui.keyboard.EnterCardNumberViewModel
import com.segunfrancis.cardinfofinder.presentation.ui.result.ResultFragment
import com.segunfrancis.cardinfofinder.presentation.util.AppConstants
import com.segunfrancis.cardinfofinder.presentation.util.Result
import com.segunfrancis.cardinfofinder.presentation.util.circularProgress
import com.segunfrancis.cardinfofinder.presentation.util.showMessage
import dagger.hilt.android.AndroidEntryPoint
import io.card.payment.CardIOActivity
import io.card.payment.CreditCard
import timber.log.Timber

@AndroidEntryPoint
class OCRFragment : BaseFragment<EnterCardNumberViewModel>() {

    private var _binding: OCRFragmentBinding? = null
    private val binding get() = _binding!!
    private var resultDisplayStr = ""

    override val viewModel: EnterCardNumberViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = OCRFragmentBinding.inflate(layoutInflater)
        val scanIntent = Intent(requireContext(), CardIOActivity::class.java)
        startActivityForResult(scanIntent, AppConstants.SCAN_REQUEST_CODE)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.setImageDrawable(circularProgress(requireContext()))
    }

    override fun observeData() {
        viewModel.cardResponse.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.progressBar.isVisible = true
                }
                is Result.Error -> {
                    binding.progressBar.isVisible = false
                    binding.root.showMessage(getString(result.formattedErrorMessage))
                    navigateBack()
                    Timber.e(result.error)
                }
                is Result.Success -> {
                    binding.progressBar.isVisible = false
                    Timber.d(result.data.toString())
                    ResultFragment(result.data) {
                        navigateBack()
                    }.show(parentFragmentManager, "result_fragment")
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
            val scanResult: CreditCard =
                data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT)!!
            resultDisplayStr = "Card Number: ${scanResult.cardNumber}".trimIndent()
            viewModel.getCardInfo(scanResult.cardNumber)
        } else {
            resultDisplayStr = "Scan was canceled."
            navigateBack()
        }
        Timber.d(resultDisplayStr)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}