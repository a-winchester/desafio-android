package com.picpay.desafio.android.extensions

import com.picpay.desafio.android.data.local.UserEntity
import com.picpay.desafio.android.domain.User

fun User.toEntity(): UserEntity {
    return UserEntity(img = this.img, name = this.name, apiId = id, username = this.username)
}