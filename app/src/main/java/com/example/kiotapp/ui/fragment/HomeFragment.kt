package com.example.kiotapp.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.kiotapp.R
import com.example.kiotapp.databinding.HomeFragmentBinding
import com.example.kiotapp.ui.adapters.GridSpacingItemDecoration
import com.example.kiotapp.ui.adapters.HomeProductAdapters
import com.example.kiotapp.ui.viewmodel.HomeViewModel
import com.example.kiotapp.utils.checkMotionEvent
import com.example.kiotapp.utils.onBackScreen
import com.example.kiotapp.utils.onNavigate

@SuppressLint("ClickableViewAccessibility")
class HomeFragment : Fragment(){
    companion object{
        const val TAG = "HomeFragment"
        const val RECYCLER_SPAN = 2
        const val SPACE_NUMBER = 25
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
            recyclerHome.addItemDecoration(GridSpacingItemDecoration(RECYCLER_SPAN, SPACE_NUMBER, true))
        }

        initObserver()
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEffect()
        listenerOnClick()
    }

    private fun listenerOnClick() {
        binding.btnCancel.setOnClickListener {
            onBackScreen()
        }
        binding.btnDone.setOnClickListener {
            onNavigate(R.id.action_homeFragment_to_reviewOrderFragment)
        }
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