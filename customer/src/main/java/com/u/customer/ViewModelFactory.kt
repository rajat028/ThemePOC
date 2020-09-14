package com.u.customer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DemoViewModel::class.java)) {
            return DemoViewModel() as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}