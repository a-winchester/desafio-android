package com.picpay.desafio.android.domain

import com.picpay.desafio.android.data.remote.RemoteUsersRepository

class GetUsersFromRemoteUseCase(private val repository: RemoteUsersRepository) {

    suspend fun execute(): List<User> {
        return try {
            repository.getUsers()
        } catch (e: Exception) {
            emptyList()
        }
    }
}