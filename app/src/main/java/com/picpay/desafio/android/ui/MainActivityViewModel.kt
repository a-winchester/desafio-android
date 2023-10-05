package com.picpay.desafio.android.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.picpay.desafio.android.domain.GetUsersFromRemoteUseCase
import com.picpay.desafio.android.domain.User

class MainActivityViewModel(private val getUsersUseCase: GetUsersFromRemoteUseCase): ViewModel() {
    val userList: LiveData<List<User>> = liveData {
        val users = getUsersUseCase.execute()
        emit(users)
    }

    fun getUsers(): LiveData<List<User>> {
        return userList
    }
}