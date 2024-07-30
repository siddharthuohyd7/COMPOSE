package com.example.composemvvm.ui.theme.hilt

import android.app.Application
import androidx.room.Room
import com.example.composemvvm.ui.theme.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Singleton
    @Provides
    fun getMyDatabaseInstance(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "app_db").build()
    }

}