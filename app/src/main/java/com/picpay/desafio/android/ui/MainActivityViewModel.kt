package com.picpay.desafio.android.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.domain.GetUsersUseCase
import com.picpay.desafio.android.domain.User
import com.picpay.desafio.android.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel(private val getUsersUseCase: GetUsersUseCase): ViewModel() {
    private val _users = MutableLiveData<List<User>?>()
    val users: LiveData<List<User>?>
        get() = _users


    fun fetchUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            getUsers()
        }
    }
    private suspend fun getUsers() {
        try {
            val result = getUsersUseCase.execute()
            if (result is Result.Success) {
                withContext(Dispatchers.Main) {
                    _users.value = result.data
                }
            } else if (result is Result.Error) {
                Log.e("MainActivityViewModel", "Error fetching users", result.exception)
            }
        } catch (e: Exception) {
            Log.e("MainActivityViewModel", "Unexpected error", e)
        }
    }
}