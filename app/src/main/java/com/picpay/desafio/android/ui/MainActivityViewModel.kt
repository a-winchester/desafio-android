package com.picpay.desafio.android.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.GetUsersUseCase
import com.picpay.desafio.android.domain.User
import kotlinx.coroutines.launch

class MainActivityViewModel(private val getUsersUseCase: GetUsersUseCase): ViewModel() {
    private val _users = MutableLiveData<List<User>?>()
    val users: LiveData<List<User>?>
        get() = _users

    init {
        viewModelScope.launch {
            getUsers()
        }
    }

    private suspend fun getUsers() {
        _users.value = getUsersUseCase.execute()
    }
}