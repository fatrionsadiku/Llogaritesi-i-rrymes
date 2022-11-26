package com.example.llogaritesirryms.login

import android.app.Application
import android.content.Context
import android.database.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.llogaritesirryms.data.Repository
import com.example.llogaritesirryms.data.User
import com.example.llogaritesirryms.data.UserDatabase
import java.util.*

class LoginViewModel(application: Application) : AndroidViewModel(application){

    var readAllData: LiveData<User>? = null
    var repository: Repository? = null

    init {
        val userDao = UserDatabase.getDatabase(
            application
        ).userDao()
        repository = Repository(userDao)
        readAllData = repository!!.getAllUsers(application)
    }

    fun getLoginDetails(username: String, password:String) : LiveData<User>? {
        readAllData = repository?.login(username,password)
        return readAllData
    }
}

