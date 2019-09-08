package com.test.paging.domain.usecase.base

interface HandlerUseCase<in Params> {

    fun execute(params: Params? = null)

}
