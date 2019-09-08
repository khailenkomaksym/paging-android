package com.test.paging.domain.repository

import com.test.paging.data.repository.Listing
import com.test.paging.data.entity.ItemsItem

interface NetworkRepository {

    fun fetchRepositories(query: String): Listing<ItemsItem>

    fun retry()

    fun refresh(query: String)

    fun clear()

}