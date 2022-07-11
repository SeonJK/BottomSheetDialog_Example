package com.example.bottomsheetdialog_example

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.bottomsheetdialog_example.databinding.ActivityMainBinding
import com.skt.Tmap.TMapView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var startBottomSheetView: StartBottomSheetFragment
    private lateinit var destBottomSheetView: DestBottomSheetFragment
    private lateinit var inputMethodManager: InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initViews() = with(binding) {
        // 지도
        val mapView = TMapView(this@MainActivity).apply {
            setSKTMapApiKey(getString(R.string.tmap_appkey))
            setHttpsMode(true)
            setLanguage(TMapView.LANGUAGE_KOREAN)
            setIconVisibility(true)
            zoomLevel = 16
            mapType = TMapView.MAPTYPE_STANDARD
        }
        mapLayout.addView(mapView)

        // bottomSheetDialog
        startBottomSheetView = StartBottomSheetFragment()
        destBottomSheetView = DestBottomSheetFragment()

        // 포커스 처리
        startTextView.setOnClickListener {
            startBottomSheetView.show(supportFragmentManager, StartBottomSheetFragment.TAG)

        }
        destTextView.setOnClickListener {
            destBottomSheetView.show(supportFragmentManager, DestBottomSheetFragment.TAG)
        }
    }

    private fun activateKeyboard() {
        inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(binding.root, InputMethodManager.SHOW_IMPLICIT)
    }
}