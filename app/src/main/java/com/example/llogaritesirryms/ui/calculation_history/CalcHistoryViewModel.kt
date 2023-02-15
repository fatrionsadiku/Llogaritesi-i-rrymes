package com.example.llogaritesirryms.ui.calculation_history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.llogaritesirryms.data.calc.CalcPackageDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalcHistoryViewModel @Inject constructor(
    private val calcPackageDao: CalcPackageDao
) : ViewModel() {

    val calculations = calcPackageDao.getCalculatedResults().asLiveData()

}