package com.strangerweather.rootlessuimods.utils

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val page = MutableLiveData(0)

    fun onPageChanged(newValue: Int) {
        page.value = newValue
    }
}