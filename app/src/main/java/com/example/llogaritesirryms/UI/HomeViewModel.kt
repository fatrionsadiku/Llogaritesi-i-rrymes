package com.example.llogaritesirryms.UI

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.WindowManager
import android.widget.Toolbar
import androidx.lifecycle.ViewModel
import com.example.llogaritesirryms.databinding.CalcResultDialogBinding

class HomeViewModel : ViewModel() {

    fun calcVal(
        a1ETashme: Double,
        a1EKaluar: Double,
        a2ETashme: Double,
        a2EKaluar: Double
    ): String {
        val a1 = (a1ETashme - a1EKaluar) * 0.0675
        val a2 = (a2ETashme - a2EKaluar) * 0.0289
        val rezultati = a1 + a2
        val roundedRes = String.format("%.2f", rezultati)
        return roundedRes
    }

}