package com.example.kiotapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kiotapp.R
import com.example.kiotapp.databinding.ChoiceEatingFragmentBinding

class ChoiceEatingFragment : Fragment() {
    private lateinit var binding : ChoiceEatingFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ChoiceEatingFragmentBinding.inflate(layoutInflater)
        return binding.root
    }
}