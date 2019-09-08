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
constructor(private val networkRepository: NetworkRepository){

    var page: Int = 0

    fun getRepositoryList(page: Int) = networkRepository.getRepositoryList(page)

}