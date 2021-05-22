package com.example.roomdb.User

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_data")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var title: String,
    var note: String
)