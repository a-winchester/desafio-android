package com.picpay.desafio.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class UsersApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        val appModule = module {

        }

        startKoin {
            androidContext(this@UsersApplication)
            modules(appModule)
        }
    }

}