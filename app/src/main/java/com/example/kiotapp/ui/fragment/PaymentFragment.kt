package com.example.kiotapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kiotapp.R
import com.example.kiotapp.databinding.PaymentFragmentBinding

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
}