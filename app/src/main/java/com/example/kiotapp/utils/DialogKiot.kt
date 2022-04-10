package com.example.kiotapp.utils;

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kiotapp.R
import com.example.kiotapp.data.model.Order
import com.example.kiotapp.databinding.DialogKiotBinding
import com.example.kiotapp.ui.viewmodel.HomeViewModel
import com.sangtb.androidlibrary.base.action.IActionDialog
import com.sangtb.androidlibrary.utils.DialogLibrary

/*
    Copyright © 2022 UITS CO.,LTD
    Created by SangTB on 4/9/2022
*/

class DialogKiot : DialogLibrary<DialogKiotBinding>(), IDialogLibrary {
    companion object {
        const val TAG = "DialogKiot"
    }

    override val viewModel: HomeViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.action = viewModel
        binding.dialogHelper = this
        dataDialog.observe(viewLifecycleOwner){
            viewModel.abc.value = it
        }
    }

    override fun onCancel() {
        viewModel.onCancelProduct()
        super.onCancel()
    }

    override fun onAddToBill(){
        viewModel.onAddToBuild()
        super.onCancel()
    }

    override val layout: Int
        get() = R.layout.dialog_kiot
}

interface IDialogLibrary: IActionDialog<DialogKiotBinding>{
    fun onAddToBill()
}

interface IDialogKiotViewModel{
    fun onPlus()
    fun onMinus()
    fun onAddToBuild()
    val currentOrderSelected: MutableLiveData<Order>
    val isEnableMinusButton : LiveData<Boolean>
    val isEnableRemoveFromCart: MutableLiveData<Boolean>
}