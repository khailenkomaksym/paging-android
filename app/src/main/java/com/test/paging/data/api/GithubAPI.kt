package com.test.paging.data.api

import com.test.paging.data.entity.RepositoryResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubAPI {

    @GET("search/repositories?page={page}&per_page=30&q=fac")
    fun getRepositoryResponse(@Path(value = "page", encoded = true) page: Int): Single<RepositoryResponse>

}