package com.picpay.desafio.android.domain

import com.picpay.desafio.android.data.local.LocalUserRepository
import com.picpay.desafio.android.extensions.toEntity

class SaveUsersUseCase(private val repository: LocalUserRepository) {

    suspend fun execute(users: List<User>) {
        repository.saveUsers(users.map { it.toEntity() })
    }
}