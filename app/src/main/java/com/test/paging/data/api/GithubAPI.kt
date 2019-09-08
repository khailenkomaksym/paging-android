package com.test.paging.data.api

import com.test.paging.data.entity.RepositoryResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubAPI {

    @GET("search/repositories?page={page}&per_page={per_page}&q=fac")
    fun getRepositoryResponse(@Path(value = "page", encoded = true) page: Int,
                              @Path(value = "per_page", encoded = true) perPage: Int): Observable<RepositoryResponse>

}