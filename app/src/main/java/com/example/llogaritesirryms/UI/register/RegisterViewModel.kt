package com.example.llogaritesirryms.UI.register

import androidx.lifecycle.ViewModel
import com.example.llogaritesirryms.data.user.User
import com.example.llogaritesirryms.data.user.UserDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    val userDao : UserDao
) : ViewModel(){

    suspend fun registerUser(user: User){
        userDao.addUser(user)
    }
}