package com.example.llogaritesirryms.ui.calculation_history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.llogaritesirryms.data.calc.CalcPackageDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalcHistoryViewModel @Inject constructor(
    private val calcPackageDao: CalcPackageDao
) : ViewModel() {


    fun deleteAllTasks() = viewModelScope.launch {
        calcPackageDao.deleteAllTasks()
    }

    val calculations = calcPackageDao.getCalculatedResults().asLiveData()

}