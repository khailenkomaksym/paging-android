package com.test.paging.di.component

import com.test.paging.App
import com.test.paging.di.modules.ActivityContributorModule
import com.test.paging.di.modules.ApplicationModule
import com.test.paging.di.modules.NetworkModule
import com.test.paging.domain.executor.PostExecutionThread
import com.test.paging.domain.executor.ThreadExecutor
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ActivityContributorModule::class, ApplicationModule::class,
                        NetworkModule::class])
interface ApplicationComponent : AndroidInjector<App> {

    fun threadExecutor(): ThreadExecutor

    fun postExecutionThread(): PostExecutionThread

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>() {

        abstract override fun build(): ApplicationComponent
    }
}