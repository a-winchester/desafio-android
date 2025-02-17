package com.picpay.desafio.android.domain

import com.picpay.desafio.android.data.UsersRepository

class GetUsersUseCase(private val repository: UsersRepository) {

    suspend fun execute(): List<User> {
        return try {
            repository.getUsers()
        } catch (e: Exception) {
            emptyList()
        }
    }
}