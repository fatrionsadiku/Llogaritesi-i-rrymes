package com.example.llogaritesirryms.data

import androidx.lifecycle.LiveData
import com.example.llogaritesirryms.data.user.User
import com.example.llogaritesirryms.data.user.UserDao
import javax.inject.Inject

class Repository @Inject constructor(
    private val userDao : UserDao
) {

    var readAllData : LiveData<User>? = null

    suspend fun insert(user : User){
        return userDao.addUser(user)
    }

    fun getAllUsers() : LiveData<User>? {
        readAllData = userDao.readAllData()
        return readAllData
    }

    fun login(username : String,password : String) : LiveData<User>? {
        var user: LiveData<User>?
        user = userDao.loginUser(username,password)
        return user
    }
}