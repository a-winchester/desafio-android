package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.data.UsersRepository
import com.picpay.desafio.android.data.UsersRepositoryImpl
import com.picpay.desafio.android.domain.GetUsersUseCase
import com.picpay.desafio.android.ui.MainActivityViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class UsersApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        val appModule = module {
            single<UsersRepository> { UsersRepositoryImpl() }
            single { GetUsersUseCase(get()) }
            viewModel { MainActivityViewModel(get()) }
        }

        startKoin {
            androidContext(this@UsersApplication)
            modules(appModule)
        }
    }

}