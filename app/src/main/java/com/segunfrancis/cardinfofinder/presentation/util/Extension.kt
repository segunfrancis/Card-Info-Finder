package com.segunfrancis.cardinfofinder.presentation.util

import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.google.android.material.snackbar.Snackbar
import com.segunfrancis.cardinfofinder.R

fun <T> MutableLiveData<T>.toLiveData(): LiveData<T> = this

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

fun circularProgress(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 5.0F
        centerRadius = 40.0F
        setColorSchemeColors(Color.rgb(255, 68, 68), Color.rgb(3, 218, 197))
        start()
    }
}