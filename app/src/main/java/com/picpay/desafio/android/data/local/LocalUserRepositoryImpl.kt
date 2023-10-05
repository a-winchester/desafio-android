package com.picpay.desafio.android.data.local

import androidx.lifecycle.LiveData

class LocalUserRepositoryImpl(private val userDao: UserDao): LocalUserRepository {
    val allUsers: LiveData<List<UserEntity>> = userDao.getAll()

    override suspend fun getSavedUsers(): LiveData<List<UserEntity>> {
        return userDao.getAll()
    }

    override suspend fun saveUsers(users: List<UserEntity>) {
        userDao.insertAll(users)
    }
}