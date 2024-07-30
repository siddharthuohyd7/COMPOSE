package com.example.composemvvm.ui.theme.repository

import com.example.composemvvm.ui.theme.room.User

abstract class LoginRepository {

    abstract suspend fun authenticate(email :String,password :String): User?

}