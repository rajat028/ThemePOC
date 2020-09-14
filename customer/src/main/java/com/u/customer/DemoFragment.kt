package com.u.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.demo.core.BackgroundSource
import com.demo.core.BaseAdapter
import com.demo.core.BaseHandler
import com.u.customer.databinding.FragmentBaseBinding

class DemoFragment : Fragment(), BaseHandler<Any> {

    private lateinit var adapter: BaseAdapter<BackgroundSource>
    private lateinit var binding: FragmentBaseBinding
    private lateinit var demoViewModel: DemoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_base, container, false)
        initViews()
        return binding.root
    }

    private fun initViews() {
        adapter = BaseAdapter(R.layout.item_demo, this)
        binding.rvDemo.adapter = adapter
        adapter.updateList(demoViewModel.getObject())
    }

    override fun onClick(view: View?, data: Any?) {

    }

    private fun setupViewModel() {
        demoViewModel = ViewModelProviders.of(this, ViewModelFactory())
            .get(DemoViewModel::class.java)
        demoViewModel.passContext(context)
    }
}