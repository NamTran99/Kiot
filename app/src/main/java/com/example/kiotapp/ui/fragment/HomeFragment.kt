package com.example.kiotapp.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.kiotapp.R
import com.example.kiotapp.databinding.HomeFragmentBinding
import com.example.kiotapp.ui.adapters.HomeProductAdapters
import com.example.kiotapp.ui.viewmodel.HomeViewModel
import com.example.kiotapp.utils.checkMotionEvent

@SuppressLint("ClickableViewAccessibility")
class HomeFragment : Fragment(R.layout.home_fragment){
    companion object{
        const val TAG = "HomeFragment"
    }
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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEffect()
    }

    private fun initObserver() {
        viewModel.allProduct.observe(viewLifecycleOwner){
            Log.d(TAG, "initObserver: data for product adapter: $it")
            productAdapter.updateItems(it.toMutableList())
        }
    }
    private fun initEffect() {
        binding.tab1.imgItemTab.setOnTouchListener { _, motionEvent ->
            binding.tab1.linearItemTab.isPressed = checkMotionEvent(motionEvent)
            return@setOnTouchListener false
        }
        binding.tab2.imgItemTab.setOnTouchListener { _, motionEvent ->
            binding.tab2.linearItemTab.isPressed = checkMotionEvent(motionEvent)
            return@setOnTouchListener false
        }
        binding.tab3.imgItemTab.setOnTouchListener { _, motionEvent ->
            binding.tab3.linearItemTab.isPressed = checkMotionEvent(motionEvent)
            return@setOnTouchListener false
        }
        binding.tab0.imgItemTab.setOnTouchListener { _, motionEvent ->
            binding.tab0.linearItemTab.isPressed = checkMotionEvent(motionEvent)
            return@setOnTouchListener false
        }
    }
}