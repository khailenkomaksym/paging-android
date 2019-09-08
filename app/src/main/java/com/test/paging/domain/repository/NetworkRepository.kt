package com.test.paging.domain.repository

import com.test.paging.data.entity.RepositoryResponse
import io.reactivex.Observable

interface NetworkRepository {

    fun getRepositoryList(page: Int, perPage: Int): Observable<RepositoryResponse>

}