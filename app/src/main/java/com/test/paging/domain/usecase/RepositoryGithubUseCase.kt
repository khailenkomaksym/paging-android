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

    fun refresh(query: String) {
        networkRepository.refresh(query)
    }

    fun fetchRepositories(query: String): Listing<ItemsItem> {
        return networkRepository.fetchRepositories(query)
    }

    fun clear() {
        networkRepository.clear()
    }

}