package com.example.kiotapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kiotapp.R
import com.example.kiotapp.databinding.HomeFragmentBinding
import com.example.kiotapp.ui.adapters.HomeProductAdapters
import com.example.kiotapp.ui.viewmodel.HomeViewModel

class HomeFragment : Fragment(R.layout.home_fragment){
    private lateinit var binding: HomeFragmentBinding
    private val viewModel by activityViewModels<HomeViewModel>()
    private val productAdapter = HomeProductAdapters()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(layoutInflater)
        binding.apply {
            action = viewModel
            recyclerHome.adapter = productAdapter

        }

        initObserver()
        return binding.root
    }

    private fun initObserver() {
        viewModel.allProduct.observe(viewLifecycleOwner){
            productAdapter.updateItems(it.toMutableList())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        viewModelStore.clear()
        super.onDestroy()
    }
}