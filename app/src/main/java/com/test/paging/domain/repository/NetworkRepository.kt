package com.test.paging.domain.repository

import com.test.paging.data.entity.RepositoryResponse
import io.reactivex.Single

interface NetworkRepository {

    fun getRepositoryList(page: Int): Single<RepositoryResponse>

}