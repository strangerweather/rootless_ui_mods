package com.strangerweather.rootlessuimods.utils

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.strangerweather.rootlessuimods.components.resourceValue

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val page = MutableLiveData(0)

    fun onPageChanged(newValue: Int) {
        page.value = newValue
    }

    val convertedHex = MutableLiveData( resourceValue.value)

    fun onConvertedHexChanged(newValue: Int){
        convertedHex.value = newValue
    }
}