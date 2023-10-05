package com.picpay.desafio.android.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table")
    fun getAll(): LiveData<List<UserEntity>>

    @Insert
    suspend fun insertAll(users: List<UserEntity>)
}