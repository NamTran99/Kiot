package com.example.kiotapp.utils;

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.kiotapp.R
import com.example.kiotapp.databinding.DialogKiotBinding
import com.example.kiotapp.ui.viewmodel.HomeViewModel
import com.sangtb.androidlibrary.base.action.IActionDialog
import com.sangtb.androidlibrary.utils.DialogLibrary

/*
    Copyright © 2022 UITS CO.,LTD
    Created by SangTB on 4/9/2022
*/

class DialogKiot : DialogLibrary<DialogKiotBinding>() {
    companion object {
        const val TAG = "DialogKiot"
    }

    override val viewModel: HomeViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.action = viewModel
        dataDialog.observe(viewLifecycleOwner){
            viewModel.abc.value = it
        }
    }

    override val layout: Int
        get() = R.layout.dialog_kiot
}

interface IDialogLibrary: IActionDialog<DialogKiotBinding>{
    fun cancelDialog()
}

interface IDialogKiotViewModel{

}