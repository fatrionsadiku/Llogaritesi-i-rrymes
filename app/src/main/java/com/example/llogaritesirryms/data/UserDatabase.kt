package com.example.llogaritesirryms.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.llogaritesirryms.data.calc.CalcInfo
import com.example.llogaritesirryms.data.calc.CalcPackageDao
import com.example.llogaritesirryms.data.user.User
import com.example.llogaritesirryms.data.user.UserDao


@Database(entities = [User::class, CalcInfo::class], version = 3)
abstract class UserDatabase() : RoomDatabase() {

    abstract fun calcDao(): CalcPackageDao
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