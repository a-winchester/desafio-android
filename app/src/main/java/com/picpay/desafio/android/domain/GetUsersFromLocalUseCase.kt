package com.picpay.desafio.android.domain

import com.picpay.desafio.android.data.local.LocalUserRepository

class GetUsersFromLocalUseCase(private val repository: LocalUserRepository) {

    suspend fun execute(): List<User> {
        return try {
            repository.getSavedUsers().value?.map { user ->
                user.toUser()

            } ?: emptyList()

        } catch (e: Exception) {
            emptyList()
        }
    }
}