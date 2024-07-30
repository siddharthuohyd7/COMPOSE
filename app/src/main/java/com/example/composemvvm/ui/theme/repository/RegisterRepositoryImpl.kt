package com.example.composemvvm.ui.theme.repository

import com.example.composemvvm.ui.theme.room.AppDatabase
import com.example.composemvvm.ui.theme.room.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(val appDatabase: AppDatabase) :
    RegisterRepository() {
    override suspend fun storeUser(user: User) {
        appDatabase.userDao().insertUser(user)
    }

    override suspend fun isUserAlreadyExist(email: String): User? {
        return appDatabase.userDao().isUserExist(email)
    }
}