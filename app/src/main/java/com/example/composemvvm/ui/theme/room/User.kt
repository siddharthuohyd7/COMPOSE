package com.example.composemvvm.ui.theme.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val firstname: String,
    val lastname: String,
    val email: String,
    val password: String,
    val createdAt: Date
)
