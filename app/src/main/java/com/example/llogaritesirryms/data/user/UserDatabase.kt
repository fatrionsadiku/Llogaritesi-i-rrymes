package com.example.llogaritesirryms.data.user

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.llogaritesirryms.data.calc.CalcInfo
import com.example.llogaritesirryms.data.calc.CalcPackageDao
import com.example.llogaritesirryms.data.user.User
import com.example.llogaritesirryms.data.user.UserDao
import com.example.llogaritesirryms.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider


@Database(entities = [User::class, CalcInfo::class], version = 3)
abstract class UserDatabase() : RoomDatabase() {

    abstract fun calcDao(): CalcPackageDao
    abstract fun userDao(): UserDao
}