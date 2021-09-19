package com.interview.test.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.interview.test.Network.Apihelper
import com.interview.test.Repository.UsersRepository

class UsersViewModelFactory (private val apihelper: Apihelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UsersViewModel::class.java)) {
            return UsersViewModel(UsersRepository(apihelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}