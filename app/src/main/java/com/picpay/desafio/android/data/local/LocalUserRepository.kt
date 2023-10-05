package com.picpay.desafio.android.data.local

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

interface LocalUserRepository {
    @WorkerThread
    suspend fun getSavedUsers(): LiveData<List<UserEntity>>

    @WorkerThread
    suspend fun saveUsers(users: List<UserEntity>)
}