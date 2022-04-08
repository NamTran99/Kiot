package com.example.kiotapp.utils;

import android.os.Bundle
import android.view.View
import com.example.kiotapp.R
import com.example.kiotapp.databinding.DialogKiotBinding
import com.sangtb.androidlibrary.utils.DialogLibrary

/*
    Copyright © 2022 UITS CO.,LTD
    Created by SangTB on 4/9/2022
*/

class DialogKiot : DialogLibrary<DialogKiotBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.action = this
    }

    override val layout: Int
        get() = R.layout.dialog_kiot

    override fun onAccept(accept: () -> Unit) {

    }
}
