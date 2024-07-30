package com.example.composemvvm.ui.theme.repository

import com.example.composemvvm.ui.theme.room.User
import kotlinx.coroutines.flow.Flow

abstract class RegisterRepository {

    abstract suspend fun storeUser(user: User)
    abstract suspend fun isUserAlreadyExist(email:String) :User?
}