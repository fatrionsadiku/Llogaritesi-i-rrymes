package com.example.llogaritesirryms.login

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.llogaritesirryms.data.Repository
import java.lang.IllegalArgumentException

class LoginViewModelFactory(private val repo : Repository, private val application: Application) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)){
            return LoginViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}