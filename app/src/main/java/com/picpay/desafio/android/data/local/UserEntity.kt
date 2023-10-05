package com.picpay.desafio.android.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.picpay.desafio.android.domain.User

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val img: String,
    val name: String,
    val apiId: Int,
    val username: String
) {
    fun toUser(): User {
        return User(
            img = img,
            name = name,
            id = apiId,
            username = username
        )
    }
}
