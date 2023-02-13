package com.example.llogaritesirryms.UI.viewmodels

import androidx.lifecycle.LiveData
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