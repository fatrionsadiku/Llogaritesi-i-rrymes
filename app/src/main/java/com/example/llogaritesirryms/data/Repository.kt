package com.example.llogaritesirryms.data

import android.content.Context
import androidx.lifecycle.LiveData

class Repository(private val userDao : UserDao) {


    var readAllData : LiveData<User>? = null
    var db : UserDatabase? = null

     fun initializeDB(context: Context) : UserDatabase {
        return UserDatabase.getDatabase(context)
    }


    suspend fun insert(user : User){
        return userDao.addUser(user)
    }

    fun getAllUsers(ctx : Context) : LiveData<User>? {
        db = initializeDB(ctx)
        readAllData = db!!.userDao().readAllData()
        return readAllData
    }

    fun login(username : String,password : String) : LiveData<User>? {
        var user: LiveData<User>?
        user = db?.userDao()?.loginUser(username,password)
        return user
    }
}