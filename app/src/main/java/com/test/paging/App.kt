package com.test.paging

import com.test.paging.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out App> =
        DaggerApplicationComponent.builder().create(this)

    override fun onCreate() {
        super.onCreate()
    }

}