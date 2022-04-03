package com.example.kiotapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kiotapp.R
import com.example.kiotapp.databinding.SplashFragmentBinding
import com.example.kiotapp.ui.MainActivity

class SplashFragment : Fragment() {
    private lateinit var binding : SplashFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SplashFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.linear1.setOnClickListener {
            (activity as? MainActivity)?.onNavigate(R.id.action_splashFragment_to_choiceEatingFragment)
        }
    }
}