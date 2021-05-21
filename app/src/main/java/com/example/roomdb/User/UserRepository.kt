package com.example.roomdb.User

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class UserRepository(application: Application) {

    private val userDao: UserDao?
    private var notes: LiveData<List<UserEntity>>? = null

    init {
        val db = UserDatabase.getDatabase(application.applicationContext)
        userDao = db.userDao()
        notes = userDao.readAllNote()
    }

    fun getNotes(): LiveData<List<UserEntity>>? {
        return notes
    }

    fun insert(note: UserEntity) = runBlocking {
        this.launch(Dispatchers.IO) {
            userDao?.insertNote(note)
        }
    }

    fun delete(note: UserEntity) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                userDao?.deleteNote(note)
            }
        }
    }

    fun update(note: UserEntity) = runBlocking {
        this.launch(Dispatchers.IO) {
            userDao?.updateNote(note)
        }
    }

}