package com.picpay.desafio.android.domain

import com.picpay.desafio.android.data.UsersRepository
import com.picpay.desafio.android.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetUsersUseCase(private val repository: UsersRepository) {

    suspend fun execute(): Result<List<User>> {
        return try {
            val users = withContext(Dispatchers.IO) {
                repository.getUsers()
            }
            Result.Success(users)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}