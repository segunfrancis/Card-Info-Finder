package com.segunfrancis.cardinfofinder.presentation.ui.keyboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.segunfrancis.cardinfofinder.domain.CardInfoEntity
import com.segunfrancis.cardinfofinder.presentation.util.Result
import com.segunfrancis.cardinfofinder.presentation.util.toLiveData
import com.segunfrancis.cardinfofinder.usecase.GetCardInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnterCardNumberViewModel @Inject constructor(private val useCase: GetCardInfoUseCase) : ViewModel() {

    private val _cardResponse: MutableLiveData<Result<CardInfoEntity>> = MutableLiveData()
    val cardResponse = _cardResponse.toLiveData()

    fun getCardInfo(number: String) {
        viewModelScope.launch {
            useCase(number)
                .onStart { _cardResponse.postValue(Result.Loading) }
                .catch { _cardResponse.postValue(Result.Error(it)) }
                .collect { _cardResponse.postValue(Result.Success(it)) }
        }
    }
}