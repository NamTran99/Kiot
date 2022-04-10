package com.example.kiotapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.kiotapp.R
import com.example.kiotapp.databinding.CompleteOrderFragmentBinding
import com.example.kiotapp.ui.viewmodel.HomeViewModel
import com.example.kiotapp.utils.onNavigate

class CompleteOrderFragment : Fragment(R.layout.complete_order_fragment) {
    private lateinit var binding: CompleteOrderFragmentBinding
    private val viewModel by activityViewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CompleteOrderFragmentBinding.inflate(layoutInflater)
        binding.apply {
            lifecycleOwner = this@CompleteOrderFragment
            action = viewModel
        }
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCancel.setOnClickListener {
            viewModel.saveDataToServer()
            onNavigate(R.id.action_completeOrderFragment_to_splashFragment)
        }
    }

    override fun onDestroy() {
        activity?.viewModelStore?.clear()
        super.onDestroy()
    }
}