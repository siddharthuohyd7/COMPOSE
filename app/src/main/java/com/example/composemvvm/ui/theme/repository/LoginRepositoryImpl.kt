package com.example.composemvvm.ui.theme.repository

import com.example.composemvvm.ui.theme.room.AppDatabase
import com.example.composemvvm.ui.theme.room.User
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(val appDatabase: AppDatabase) : LoginRepository() {
    override suspend fun authenticate(email: String, password: String): User? {
        return appDatabase.userDao().authenticate(email, password);
    }

}