package com.test.paging.domain.usecase

import com.test.paging.data.entity.ItemsItem
import com.test.paging.data.repository.Listing
import com.test.paging.domain.repository.NetworkRepository
import javax.inject.Inject

class RepositoryGithubUseCase
@Inject
constructor(private val networkRepository: NetworkRepository){

    fun retry() {
        networkRepository.retry()
    }

    fun refresh() {
        networkRepository.refresh()
    }

    fun fetchRepositories(): Listing<ItemsItem> {
        return networkRepository.fetchRepositories()
    }

    fun clear() {
        networkRepository.clear()
    }

}