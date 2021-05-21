package com.example.roomdb.User

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class UserViewModel(application: Application): AndroidViewModel(application) {

    private var UserRepository = UserRepository(application)
    private var notes: LiveData<List<UserEntity>>? = UserRepository.getNotes()

    fun insertNote(note: UserEntity) {
        UserRepository.insert(note)
    }

    fun getNotes(): LiveData<List<UserEntity>>? {
        return notes
    }

    fun deleteNote(note: UserEntity) {
        UserRepository.delete(note)
    }

    fun updateNote(note: UserEntity) {
        UserRepository.update(note)
    }

}