package com.pocdarktheme

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.demo.core.BackgroundSource
import com.demo.core.BaseAdapter
import com.demo.core.BaseHandler
import com.pocdarktheme.databinding.ActivityFirstBinding
import kotlinx.android.synthetic.main.activity_first.*

class FirstActivity : AppCompatActivity(), BaseHandler<Any> {

    private lateinit var adapter: BaseAdapter<BackgroundSource>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityFirstBinding>(this, R.layout.activity_first)

        btnNext.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    override fun onClick(view: View?, data: Any?) {

    }
}