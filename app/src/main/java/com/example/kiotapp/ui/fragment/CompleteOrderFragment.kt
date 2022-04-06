package com.example.kiotapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.kiotapp.R
import com.example.kiotapp.databinding.CompleteOrderFragmentBinding
import com.example.kiotapp.ui.viewmodel.CompleteOrderViewModel
import com.example.kiotapp.ui.viewmodel.ReviewOrderViewModel
import com.example.kiotapp.utils.onNavigate

class CompleteOrderFragment : Fragment(R.layout.complete_order_fragment) {
    private lateinit var binding: CompleteOrderFragmentBinding
    private val viewModel by activityViewModels<CompleteOrderViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CompleteOrderFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCancel.setOnClickListener {
            onNavigate(R.id.action_completeOrderFragment_to_splashFragment)
        }
    }
}