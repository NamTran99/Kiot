package com.example.kiotapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kiotapp.R
import com.example.kiotapp.databinding.PaymentFragmentBinding
import com.example.kiotapp.utils.onNavigate
import com.example.kiotapp.utils.opacityView

class PaymentFragment : Fragment(R.layout.payment_fragment) {
    private lateinit var binding : PaymentFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PaymentFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.linear1.opacityView()
        binding.linear2.opacityView()

        binding.linear2.setOnClickListener {
            onNavigate(R.id.action_paymentFragment_to_completeOrderFragment)
        }
        binding.linear1.setOnClickListener {
            onNavigate(R.id.action_paymentFragment_to_completeOrderFragment)
        }
    }
}