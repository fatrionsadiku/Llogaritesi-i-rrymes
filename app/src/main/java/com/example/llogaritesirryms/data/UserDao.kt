package com.example.llogaritesirryms.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addUser(user : User)

    @Query("SELECT * FROM user_info ORDER BY id DESC")
    fun getAllUsers(): List<User>

    @Query("SELECT * FROM user_info")
    fun readAllData() : LiveData<User>


    @Query("SELECT * FROM user_info WHERE userName LIKE :userName and password LIKE :password")
    fun loginUser(userName : String,  password : String) : LiveData<User>

}