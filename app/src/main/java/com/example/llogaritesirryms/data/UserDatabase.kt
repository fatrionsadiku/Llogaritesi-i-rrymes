package com.example.llogaritesirryms.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized


@Database(entities = [User::class], version = 1)
abstract class UserDatabase() : RoomDatabase() {

    abstract fun userDao(): UserDao


//    companion object {
//        @Volatile
//        private var INSTANCE: UserDatabase? = null
//        @OptIn(InternalCoroutinesApi::class)
//        fun getDatabase(ctx : Context) : UserDatabase{
//            val tempInstance = INSTANCE
//            if(tempInstance != null){
//                return tempInstance
//            }
//            synchronized(this){
//                val instance = Room.databaseBuilder(
//                    ctx.applicationContext,
//                    UserDatabase::class.java,
//                    "user_database"
//                ).build()
//                INSTANCE = instance
//                return instance
//            }
//        }
//    }

}