package com.test.paging.domain.usecase

import com.test.paging.data.entity.RepositoryResponse
import com.test.paging.domain.executor.PostExecutionThread
import com.test.paging.domain.executor.ThreadExecutor
import com.test.paging.domain.repository.NetworkRepository
import com.test.paging.domain.usecase.base.ObservableUseCase
import io.reactivex.Observable
import javax.inject.Inject

class RepositoryGithubUseCase
@Inject
constructor(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val networkRepository: NetworkRepository
) : ObservableUseCase<RepositoryResponse, Unit>(threadExecutor, postExecutionThread) {

    var page: Int = 0
    var perPage: Int = 0

    override fun buildUseCaseObservable(params: Unit?): Observable<RepositoryResponse> {
        return networkRepository.getRepositoryList(page, perPage)
    }

}