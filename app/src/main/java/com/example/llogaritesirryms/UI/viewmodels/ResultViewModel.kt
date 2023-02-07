package com.example.llogaritesirryms.UI.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.llogaritesirryms.data.calc.CalcInfo
import com.example.llogaritesirryms.data.calc.CalcPackageDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val calcPackageDao: CalcPackageDao
) : ViewModel() {



    fun insertCalculatedPackage(calcPackage: CalcInfo) = viewModelScope.launch {
        calcPackageDao.registerCalculatedResult(calcPackage)
    }

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