package com.test.paging.domain.usecase.base

import com.test.paging.domain.executor.PostExecutionThread
import com.test.paging.domain.executor.ThreadExecutor
import com.test.paging.domain.usecase.EmptyObserver
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver

abstract class ObservableUseCase<Results, in Params>(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : BaseReactiveUseCase(threadExecutor, postExecutionThread) {

    abstract fun buildUseCaseObservable(params: Params? = null): Observable<Results>

    fun execute(observer: DisposableObserver<Results> = EmptyObserver(), params: Params? = null) {
        val observable = buildUseCaseObservableWithSchedulers(params)
        addDisposable(observable.subscribeWith(observer))
    }

    private fun buildUseCaseObservableWithSchedulers(params: Params?): Observable<Results> {
        return buildUseCaseObservable(params)
            .subscribeOn(threadExecutorScheduler)
            .observeOn(postExecutionThreadScheduler)
    }
}