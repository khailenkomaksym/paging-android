package com.test.paging.data.repository

import android.util.Log
import androidx.lifecycle.Transformations.switchMap
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.test.paging.data.api.GithubAPI
import com.test.paging.data.entity.ItemsItem
import com.test.paging.data.repository.datasource.RepositoryDataSourceFactory
import com.test.paging.domain.repository.NetworkRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkRepositoryImpl
@Inject constructor(val githubAPI: GithubAPI) : NetworkRepository {

    private val compositeDisposable = CompositeDisposable()

    private val pageSize = 15

    private val repositoryDataSourceFactory: RepositoryDataSourceFactory = RepositoryDataSourceFactory(githubAPI, compositeDisposable)

    override fun fetchRepositories(): Listing<ItemsItem> {

        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()

        val pagedList = LivePagedListBuilder(repositoryDataSourceFactory, config).build()

        return Listing(
            pagedList,
            switchMap(repositoryDataSourceFactory.source) { it.getNetworkState() },
            switchMap(repositoryDataSourceFactory.source) { it.getInitialLoad() }
        )
    }

    override fun retry() {
        repositoryDataSourceFactory.getRepositoryDataSource().value?.retry()
    }

    override fun refresh(query: String) {
        repositoryDataSourceFactory.query = query
        repositoryDataSourceFactory.getRepositoryDataSource().value?.invalidate()
    }

    override fun clear() {
        compositeDisposable.dispose()
    }

}