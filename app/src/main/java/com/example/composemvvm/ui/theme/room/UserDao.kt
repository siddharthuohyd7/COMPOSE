package com.example.composemvvm.ui.theme.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user ORDER BY createdAt DESC")
    fun getAllUsersData(): Flow<List<User>>

    @Query("SELECT * FROM user where email= :email")
    fun isUserExist(email: String): User?

    @Query("SELECT * FROM user where email= :email AND password =:password")
    fun authenticate(email: String, password: String): User?
}