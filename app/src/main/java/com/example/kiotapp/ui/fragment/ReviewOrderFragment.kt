package com.example.kiotapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.kiotapp.R
import com.example.kiotapp.databinding.ReviewOrderFragmentBinding
import com.example.kiotapp.ui.adapters.ReviewOrderAdapters
import com.example.kiotapp.ui.viewmodel.HomeViewModel
import com.example.kiotapp.utils.DialogKiot
import com.example.kiotapp.utils.onBackScreen
import com.example.kiotapp.utils.onNavigate

class ReviewOrderFragment : Fragment() {
    private lateinit var binding : ReviewOrderFragmentBinding
    private val viewModel by activityViewModels<HomeViewModel>()
    private val reviewAdapter = ReviewOrderAdapters()
    private val dialog = DialogKiot()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ReviewOrderFragmentBinding.inflate(layoutInflater)
        reviewAdapter.setOnButtonClickListener{ _, item, _ ->
            viewModel.onClickOrderInformation(item)
            dialog.setTitle(item.name).show(parentFragmentManager, DialogKiot.TAG)
        }

        binding.apply {
            action = viewModel
            recyclerView.adapter = reviewAdapter
            this.lifecycleOwner = this@ReviewOrderFragment
        }

        viewModel.orderInformation.observe(viewLifecycleOwner){
            reviewAdapter.updateItems(it.toMutableList())
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnDone.setOnClickListener {
            onNavigate(R.id.action_reviewOrderFragment_to_paymentFragment)
        }
        binding.btnCancel.setOnClickListener {
            onBackScreen()
        }
    }
}