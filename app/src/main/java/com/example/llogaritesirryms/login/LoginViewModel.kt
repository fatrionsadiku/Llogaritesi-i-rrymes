package com.example.llogaritesirryms.login

import android.app.Application
import android.content.Context
import android.database.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.llogaritesirryms.data.Repository
import com.example.llogaritesirryms.data.User
import com.example.llogaritesirryms.data.UserDatabase
import com.example.llogaritesirryms.di.ApplicationScope
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import java.util.concurrent.Flow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository : Repository) : ViewModel(){

    var readAllData: LiveData<User>? = null

    init {
        readAllData = repository.getAllUsers()
    }

    fun getLoginDetails(username: String, password:String) : LiveData<User>? {
        readAllData = repository.login(username,password)
        return readAllData
    }
}

