package com.test.paging.domain.usecase.base

interface SynchronousUseCase<out Results, in Params> {

    fun execute(params: Params? = null): Results

}
