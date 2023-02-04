package com.example.llogaritesirryms.UI.viewmodels

import androidx.lifecycle.ViewModel

class ResultViewModel : ViewModel() {


    fun calculateA1Spent(a1ETashme: Int, a1EKaluar: Int)=
        String.format("%.2f", (a1ETashme - a1EKaluar) * 0.0675)

    fun calculateA2Spent(a2ETashme: Int, a2EKaluar: Int)=
         String.format("%.2f", (a2ETashme - a2EKaluar) * 0.0289)

    fun calculateFinalPrice(
        a1ETashme: Int,
        a1EKaluar: Int,
        a2ETashme: Int,
        a2EKaluar: Int
    ): String {
        val a1 = (a1ETashme - a1EKaluar) * 0.0675
        val a2 = (a2ETashme - a2EKaluar) * 0.0289
        val rezultati = a1 + a2
        val roundedRes = String.format("%.2f", rezultati)
        return roundedRes
    }

}