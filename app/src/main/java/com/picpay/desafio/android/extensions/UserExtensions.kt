package com.picpay.desafio.android.extensions

import com.picpay.desafio.android.data.local.UserEntity
import com.picpay.desafio.android.domain.User

fun User.toEntity(): UserEntity {
    return UserEntity(this.img, this.name, this.id, this.username)
}