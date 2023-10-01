package com.picpay.desafio.android.data

import com.picpay.desafio.android.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsersRepositoryImpl: UsersRepository {
    override suspend fun getUsers(): List<User> {
        return try {
            val users = withContext(Dispatchers.IO) {
                NetworkModule.picPayService.getUsers()
            }
            users
        } catch (e: Exception) {
            emptyList()
        }
    }
}