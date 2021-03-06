package com.example.kiotapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kiotapp.R
import com.example.kiotapp.databinding.ChoiceEatingFragmentBinding
import com.example.kiotapp.utils.onNavigate
import com.example.kiotapp.utils.opacityView
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ChoiceEatingFragment : Fragment() {
    private lateinit var binding: ChoiceEatingFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ChoiceEatingFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.linear1.opacityView()
        binding.linear2.opacityView()

        binding.linear1.setOnClickListener {
            onNavigate(R.id.action_choiceEatingFragment_to_homeFragment)
        }
        binding.linear2.setOnClickListener {
            onNavigate(R.id.action_choiceEatingFragment_to_homeFragment)
        }
    }
}



