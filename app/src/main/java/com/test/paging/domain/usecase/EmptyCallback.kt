package com.test.paging.domain.usecase

import androidx.annotation.NonNull

interface EmptyCallback<T> {

    fun onSuccess() {}

    fun onError(@NonNull t: T) {}

}