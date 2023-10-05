package com.picpay.desafio.android.data.remote

import com.picpay.desafio.android.domain.User

interface RemoteUsersRepository {
    suspend fun getUsers(): List<User>
}