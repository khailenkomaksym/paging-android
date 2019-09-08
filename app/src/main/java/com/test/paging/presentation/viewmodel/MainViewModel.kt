package com.test.paging.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.test.paging.data.NetworkState
import com.test.paging.data.entity.ItemsItem
import com.test.paging.data.repository.Listing
import com.test.paging.domain.repository.NetworkRepository
import com.test.paging.domain.usecase.RepositoryGithubUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(val repositoryGithubUseCase: RepositoryGithubUseCase) : ViewModel() {

    private val repositoryListing: Listing<ItemsItem>

    init {
        repositoryListing = repositoryGithubUseCase.fetchRepositories()
    }

    fun retry() {
        repositoryGithubUseCase.retry()
    }

    fun refresh() {
        repositoryGithubUseCase.refresh()
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return repositoryListing.networkState()
    }

    fun getRefreshState(): LiveData<NetworkState> {
        return repositoryListing.refreshState()
    }

    override fun onCleared() {
        super.onCleared()
        repositoryGithubUseCase.clear()
    }

}