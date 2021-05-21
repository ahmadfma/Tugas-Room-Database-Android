package com.example.roomdb.User

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNote(note: UserEntity)

    @Query("SELECT * FROM user_data")
    suspend fun readAllNote(): LiveData<List<UserEntity>>

    @Delete
    suspend fun deleteNote(note: UserEntity)

    @Update
    suspend fun updateNote(note: UserEntity)

}