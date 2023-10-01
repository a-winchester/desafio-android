package com.picpay.desafio.android.data

import com.picpay.desafio.android.domain.User

interface UsersRepository {
    suspend fun getUsers(): List<User>
}