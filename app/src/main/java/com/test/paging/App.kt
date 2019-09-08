package com.test.paging

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out App> =
        DaggerApplicationComponent.builder().create(this)

    override fun onCreate() {
        super.onCreate()
    }

}