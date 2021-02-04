package com.segunfrancis.cardinfofinder.presentation.util

import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.Snackbar
import com.segunfrancis.cardinfofinder.R

fun <T>MutableLiveData<T>.toLiveData(): LiveData<T> = this

fun View.showMessage(message: String) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    snackbar.setBackgroundTint(ContextCompat.getColor(this.context, R.color.red))
    snackbar.show()
}

fun View.enableState(enable: Boolean) {
    if (enable) {
        alpha = 1F
        isEnabled = true
    } else {
        alpha = 0.5F
        isEnabled = false
    }
}