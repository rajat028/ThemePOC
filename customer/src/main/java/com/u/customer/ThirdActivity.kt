package com.u.customer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ThirdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        initViews()
    }

    private fun initViews() {
        supportFragmentManager.beginTransaction().replace(R.id.content_frame, DemoFragment())
            .commit()
    }
}