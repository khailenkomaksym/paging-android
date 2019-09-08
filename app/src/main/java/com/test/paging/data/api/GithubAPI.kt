package com.test.paging.data.api

import com.test.paging.data.entity.RepositoryResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubAPI {

    @GET("search/repositories?per_page=30&q=fac")
    fun getRepositoryResponse(@Query("page") page: Int): Single<RepositoryResponse>

}