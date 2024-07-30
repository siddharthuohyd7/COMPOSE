package com.example.composemvvm.ui.theme.hilt

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.composemvvm.ui.theme.repository.LoginRepository
import com.example.composemvvm.ui.theme.repository.LoginRepositoryImpl
import com.example.composemvvm.ui.theme.repository.RegisterRepository
import com.example.composemvvm.ui.theme.repository.RegisterRepositoryImpl
import com.example.composemvvm.ui.theme.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providesRegisterRepositoryInstance(appDatabase: AppDatabase) :RegisterRepository{
        return RegisterRepositoryImpl(appDatabase)
    }

    @Singleton
    @Provides
    fun providesLoginRepositoryInstance(appDatabase: AppDatabase) :LoginRepository{
        return LoginRepositoryImpl(appDatabase)
    }



}