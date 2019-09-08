package com.test.paging.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.test.paging.domain.repository.NetworkRepository
import com.test.paging.domain.usecase.RepositoryGithubUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(val repositoryGithubUseCase: RepositoryGithubUseCase) : ViewModel() {

    fun getSomething() {
    }

}