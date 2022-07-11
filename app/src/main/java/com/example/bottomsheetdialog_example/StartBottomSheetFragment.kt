package com.example.bottomsheetdialog_example

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService
import com.example.bottomsheetdialog_example.databinding.StartBottomSheetDialogBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * BottomSheetDialog_Example
 * Created by SeonJK
 * Date: 2022-07-06
 * Time: 오후 4:01
 * */
class StartBottomSheetFragment : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "ModalBottomSheet"
    }

    private var binding: StartBottomSheetDialogBinding? = null

    private lateinit var inputMethodManager: InputMethodManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = StartBottomSheetDialogBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        dialog.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            setupRatio(bottomSheetDialog)
        }

        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activateKeyboard()
    }

    private fun setupRatio(bottomSheetDialog: BottomSheetDialog) {
        val behavior = BottomSheetBehavior.from(binding!!.linearLayout)
        val layoutParams = binding!!.linearLayout.layoutParams
        layoutParams.height = getBottomSheetDialogDefaultHeight()
        binding!!.linearLayout.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun activateKeyboard() {
        binding!!.startEditText.requestFocus()
        binding!!.startEditText.postDelayed(Runnable {
            inputMethodManager =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(binding?.startEditText,
                InputMethodManager.SHOW_IMPLICIT)
        }, 100)
    }

    private fun getBottomSheetDialogDefaultHeight(): Int =
        getWindowHeight() * 85 / 100

    private fun getWindowHeight(): Int {
        val displayMetrics = DisplayMetrics()

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            val display = activity?.display
            display?.getMetrics(displayMetrics)
        } else {
            val display = activity?.windowManager?.defaultDisplay
            display?.getMetrics(displayMetrics)
        }
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)

        return displayMetrics.heightPixels
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}