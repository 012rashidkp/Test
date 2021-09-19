package com.interview.test.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.interview.test.Network.Resources
import com.interview.test.Repository.UsersRepository
import kotlinx.coroutines.Dispatchers

class UsersViewModel(private val usersRepository: UsersRepository):ViewModel() {
    fun getitems() = liveData(Dispatchers.IO) {
        emit(Resources.loading(data = null))
        try {
            emit(Resources.success(data = usersRepository.getapi()))
            System.out.println("got result")
        } catch (exception: Exception) {
            emit(Resources.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

}