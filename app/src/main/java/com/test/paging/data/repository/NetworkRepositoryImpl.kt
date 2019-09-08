package com.test.paging.data.repository

import com.test.paging.data.api.GithubAPI
import com.test.paging.data.entity.RepositoryResponse
import com.test.paging.domain.repository.NetworkRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepositoryImpl
@Inject constructor(val githubAPI: GithubAPI) : NetworkRepository {

    override fun getRepositoryList(page: Int): Single<RepositoryResponse> {
        return githubAPI.getRepositoryResponse(page)
    }

}