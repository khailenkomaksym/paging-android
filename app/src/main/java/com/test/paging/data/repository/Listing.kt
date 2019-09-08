package com.test.paging.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.test.paging.data.NetworkState

data class Listing<T>(

    val pagedList: LiveData<PagedList<T>>,

    val networkState: LiveData<NetworkState>,

    val refreshState: LiveData<NetworkState>
)