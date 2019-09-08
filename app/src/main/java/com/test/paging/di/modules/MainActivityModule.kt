package com.test.paging.di.modules

import com.test.paging.data.repository.NetworkRepositoryImpl
import com.test.paging.domain.repository.NetworkRepository
import com.test.paging.presentation.viewmodel.MainViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityModule {

    @Binds
    abstract fun bindNetworkRepository(networkRepositoryImpl: NetworkRepositoryImpl): NetworkRepository

}