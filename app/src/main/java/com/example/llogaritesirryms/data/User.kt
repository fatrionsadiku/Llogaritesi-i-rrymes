package com.example.llogaritesirryms.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_info")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id : Int?,
    @ColumnInfo(name = "userName")
    val userName : String,
    @ColumnInfo(name = "password")
    val password : String)
