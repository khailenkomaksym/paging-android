package com.test.paging.data.repository.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.test.paging.data.api.GithubAPI
import com.test.paging.data.entity.ItemsItem
import io.reactivex.disposables.CompositeDisposable

class RepositoryDataSourceFactory(
    private val githubApi: GithubAPI,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, ItemsItem>() {

    val source = MutableLiveData<RepositoryDataSource>()

    override fun create(): DataSource<Int, ItemsItem> {
        val source = RepositoryDataSource(githubApi, compositeDisposable)
        this.source.postValue(source)
        return source
    }

    fun getRepositoryDataSource(): MutableLiveData<RepositoryDataSource> {
        return source
    }
}