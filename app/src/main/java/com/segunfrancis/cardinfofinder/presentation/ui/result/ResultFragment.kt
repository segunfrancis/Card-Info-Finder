package com.segunfrancis.cardinfofinder.presentation.ui.result

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.segunfrancis.cardinfofinder.databinding.ResultFragmentBinding
import com.segunfrancis.cardinfofinder.domain.CardInfoEntity
import com.segunfrancis.cardinfofinder.presentation.util.AppConstants

class ResultFragment(private val cardInfo: CardInfoEntity, private val whenDismissed: () -> Unit) : DialogFragment() {

    private var _binding: ResultFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ResultFragmentBinding.inflate(layoutInflater)
        populateData(cardInfo)
        return binding.root
    }

    private fun populateData(cardInfo: CardInfoEntity) {
        with(binding) {
            textScheme.text = cardInfo.scheme ?: AppConstants.DEFAULT_TEXT
            textBank.text = cardInfo.bank.name ?: AppConstants.DEFAULT_TEXT
            textCardNumberLength.text = cardInfo.number?.length?.toString() ?: AppConstants.DEFAULT_TEXT
            textCountry.text = cardInfo.country?.name ?: AppConstants.DEFAULT_TEXT
            textPrepaid.text = cardInfo.prepaid?.toString() ?: AppConstants.DEFAULT_TEXT
            textType.text = cardInfo.type ?: AppConstants.DEFAULT_TEXT
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        whenDismissed()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}