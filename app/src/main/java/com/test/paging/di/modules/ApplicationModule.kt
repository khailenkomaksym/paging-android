package com.test.paging.di.modules

import android.app.Application
import com.test.paging.App
import com.test.paging.data.executor.JobExecutor
import com.test.paging.domain.executor.PostExecutionThread
import com.test.paging.domain.executor.ThreadExecutor
import com.test.paging.presentation.UIThread
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationModule {

    @Binds
    abstract fun bindThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UIThread): PostExecutionThread

    @Binds
    abstract fun bindApplication(app: App): Application
}
