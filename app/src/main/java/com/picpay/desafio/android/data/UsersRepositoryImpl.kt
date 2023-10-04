package com.picpay.desafio.android.data

import com.picpay.desafio.android.domain.User
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class UsersRepositoryImpl: UsersRepository {
    override suspend fun getUsers(): List<User> {
        return coroutineScope {
            val deferred = async { NetworkModule.picPayService.getUsers() }
            deferred.await()
        }
    }
}