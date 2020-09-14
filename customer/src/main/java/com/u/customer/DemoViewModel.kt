package com.u.customer

import android.content.Context
import androidx.lifecycle.AndroidViewModel
import com.demo.core.BackgroundSource
import com.demo.core.CoreApplication

class DemoViewModel : AndroidViewModel(CoreApplication()) {

    lateinit var activityContext: Context

    fun getObject(): List<BackgroundSource> {
        return listOf(
            BackgroundSource(),
            BackgroundSource(),
            BackgroundSource(),
            BackgroundSource()
        )
    }

    fun passContext(context: Context?) {
        activityContext = getApplication()
    }
}