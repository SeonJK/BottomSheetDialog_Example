package com.example.bottomsheetdialog_example

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.example.bottomsheetdialog_example.databinding.DestBottomSheetDialogBinding
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
class DestBottomSheetFragment : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "ModalBottomSheet"
    }

    private var binding: DestBottomSheetDialogBinding? = null

    private lateinit var inputMethodManager: InputMethodManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DestBottomSheetDialogBinding.inflate(inflater, container, false)
        val view = binding!!.root
        return view
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
        binding!!.destEditText.requestFocus()
        binding!!.destEditText.postDelayed(Runnable {
            inputMethodManager =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.showSoftInput(binding?.destEditText,
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