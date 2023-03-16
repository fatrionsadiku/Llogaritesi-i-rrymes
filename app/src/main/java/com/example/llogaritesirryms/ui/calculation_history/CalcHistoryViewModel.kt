package com.example.llogaritesirryms.ui.calculation_history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.llogaritesirryms.data.calc.CalcInfo
import com.example.llogaritesirryms.data.calc.CalcPackageDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalcHistoryViewModel @Inject constructor(
    private val calcPackageDao: CalcPackageDao
) : ViewModel() {

    val searchQuery = MutableStateFlow("")

    private val calculationsFlow = searchQuery.flatMapLatest {
        calcPackageDao.getCalculatedResults(it)
    }

    val calculations = calculationsFlow.asLiveData()


    fun deleteAllTasks() = viewModelScope.launch {
        calcPackageDao.deleteAllRecords()
    }

    fun onRecordSwipe(currentCalculatedRecord: CalcInfo?) = viewModelScope.launch {
        calcPackageDao.deleteRecord(currentCalculatedRecord)
    }
}