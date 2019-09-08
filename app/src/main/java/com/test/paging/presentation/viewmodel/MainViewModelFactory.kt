package com.test.paging.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.paging.domain.usecase.RepositoryGithubUseCase

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(val repositoryGithubUseCase: RepositoryGithubUseCase, val query: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repositoryGithubUseCase, query) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}