package com.demo.core

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat

data class BackgroundSource(private var context: Context? = null, val title: String = "Test") :
    BaseTModel() {
    var background: Drawable =
        ContextCompat.getDrawable(CoreApplication.context, R.drawable.cs_gradient)!!
}