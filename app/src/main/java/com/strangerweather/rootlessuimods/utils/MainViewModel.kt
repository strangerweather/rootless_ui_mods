package com.strangerweather.rootlessuimods.utils

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val colorPage = MutableLiveData(0)
    fun onColorPageChanged(newColorValue: Int) {
        colorPage.value = newColorValue
    }

    val screenPage = MutableLiveData(0)
    fun onScreenPageChanged(newScreenValue: Int) {
        screenPage.value = newScreenValue
    }
}