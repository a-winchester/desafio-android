package com.picpay.desafio.android.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.picpay.desafio.android.domain.GetUsersFromLocalUseCase
import com.picpay.desafio.android.domain.GetUsersFromRemoteUseCase
import com.picpay.desafio.android.domain.SaveUsersUseCase
import com.picpay.desafio.android.domain.User

class MainActivityViewModel(
    private val getUserFromRemote: GetUsersFromRemoteUseCase,
    private val getUserFromLocal: GetUsersFromLocalUseCase,
    private val saveUsersLocally: SaveUsersUseCase,
) : ViewModel() {
    val userApiList: LiveData<List<User>> = liveData {
        val usersRemote = getUserFromRemote.execute()
        emit(usersRemote)
    }

    val userRoomList: LiveData<List<User>> = liveData {
        val usersLocal = getUserFromLocal.execute()
        emit(usersLocal)
    }

    fun getUsersFromRemote(): LiveData<List<User>> {
        return userApiList
    }

    fun getUsersFromLocal(): LiveData<List<User>> {
        return userRoomList
    }

    suspend fun saveUsersLocally(users: List<User>) {
        saveUsersLocally.execute(users)
    }
}