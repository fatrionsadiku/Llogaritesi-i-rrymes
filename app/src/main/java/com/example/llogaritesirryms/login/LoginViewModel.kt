package com.example.llogaritesirryms.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.llogaritesirryms.data.Repository
import com.example.llogaritesirryms.data.user.User
import dagger.hilt.android.lifecycle.HiltViewModel
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

