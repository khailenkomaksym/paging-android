package com.test.paging.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.paging.domain.usecase.RepositoryGithubUseCase

class MainViewModelFactory(val repositoryGithubUseCase: RepositoryGithubUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repositoryGithubUseCase) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}