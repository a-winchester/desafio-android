package com.picpay.desafio.android

import android.app.Application
import androidx.room.Room
import com.picpay.desafio.android.data.local.AppDatabase
import com.picpay.desafio.android.data.local.LocalUserRepository
import com.picpay.desafio.android.data.local.LocalUserRepositoryImpl
import com.picpay.desafio.android.data.remote.RemoteUsersRepository
import com.picpay.desafio.android.data.remote.RemoteUsersRepositoryImpl
import com.picpay.desafio.android.domain.GetUsersFromLocalUseCase
import com.picpay.desafio.android.domain.GetUsersFromRemoteUseCase
import com.picpay.desafio.android.domain.SaveUsersUseCase
import com.picpay.desafio.android.ui.MainActivityViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class UsersApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        val appDatabase = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "user_database")
            .build()

        val appModule = module {
            single { appDatabase }
            single { appDatabase.userDao() }
            single<RemoteUsersRepository> { RemoteUsersRepositoryImpl() }
            single<LocalUserRepository> { LocalUserRepositoryImpl(get()) }
            single { GetUsersFromRemoteUseCase(get()) }
            single { GetUsersFromLocalUseCase(get()) }
            single { SaveUsersUseCase(get()) }
            viewModel { MainActivityViewModel(get(), get(), get()) }
        }

        startKoin {
            androidContext(this@UsersApplication)
            modules(appModule)
        }
    }

}