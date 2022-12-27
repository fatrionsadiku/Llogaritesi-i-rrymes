package com.example.llogaritesirryms.di

import android.app.Application
import androidx.room.Room
import com.example.llogaritesirryms.data.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideDatabase(application: Application) =
        Room.databaseBuilder(application,UserDatabase::class.java,"user_database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideDao(db : UserDatabase) = db.userDao()

    @ApplicationScope
    @Singleton
    @Provides
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())
}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class ApplicationScope