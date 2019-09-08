package com.test.paging.data.repository.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.test.paging.data.api.GithubAPI
import com.test.paging.data.entity.ItemsItem
import com.test.paging.data.NetworkState
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RepositoryDataSource(
    val githubAPI: GithubAPI,
    val compositeDisposable: CompositeDisposable,
    val query: String
) : PageKeyedDataSource<Int, ItemsItem>() {

    private val networkState: MutableLiveData<NetworkState> = MutableLiveData()

    private val initialLoad: MutableLiveData<NetworkState> = MutableLiveData()

    private var retryCompletable: Completable? = null

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(
                retryCompletable!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ }, { throwable -> Log.d("err", throwable.message) })
            )
        }
    }

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, ItemsItem>) {
        networkState.postValue(NetworkState.LOADING)
        initialLoad.postValue(NetworkState.LOADING)

        compositeDisposable.add(
            githubAPI.getRepositoryResponse(1, query)
                .subscribe({ response ->
                setRetry(null)
                networkState.postValue(NetworkState.LOADED)
                initialLoad.postValue(NetworkState.LOADED)

                val items = response?.items ?: emptyList()

                callback.onResult(items, null, 2)
            },
                { throwable ->
                    setRetry { loadInitial(params, callback) }
                    val error = NetworkState.error(throwable.message)
                    networkState.postValue(error)
                    initialLoad.postValue(error)
                })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ItemsItem>) {
        networkState.postValue(NetworkState.LOADING)

        compositeDisposable.add(
            githubAPI.getRepositoryResponse(params.key, query)
                .subscribe({ response ->
                setRetry(null)
                networkState.postValue(NetworkState.LOADED)

                val items = response?.items ?: emptyList()

                callback.onResult(items, params.key + 1)
            },
                { throwable ->
                    setRetry { loadAfter(params, callback) }
                    networkState.postValue(NetworkState.error(throwable.message))
                })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ItemsItem>) {
    }

    fun getNetworkState(): MutableLiveData<NetworkState> {
        return networkState
    }

    fun getInitialLoad(): MutableLiveData<NetworkState> {
        return initialLoad
    }

    private fun setRetry(action: (() -> Unit)?) {
        if (action == null) {
            this.retryCompletable = null
        } else {
            this.retryCompletable = Completable.fromAction(action)
        }
    }
}