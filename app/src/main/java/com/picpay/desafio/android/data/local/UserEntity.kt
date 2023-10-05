package com.picpay.desafio.android.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.picpay.desafio.android.domain.User

@Entity(tableName = "user_table")
data class UserEntity(
    val img: String,
    val name: String,
    @PrimaryKey val id: Int,
    val username: String
) {
    fun toUser(): User {
        return User(
            img = img,
            name = name,
            id = id,
            username = username
        )
    }
}
