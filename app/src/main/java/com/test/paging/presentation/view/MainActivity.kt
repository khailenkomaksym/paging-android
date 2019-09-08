package com.test.paging.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.test.paging.R
import com.test.paging.domain.usecase.RepositoryGithubUseCase
import com.test.paging.presentation.base.BaseActivity
import com.test.paging.presentation.viewmodel.MainViewModel
import com.test.paging.presentation.viewmodel.MainViewModelFactory
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var repositoryGithubUseCase: RepositoryGithubUseCase

    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this, MainViewModelFactory(repositoryGithubUseCase))[MainViewModel::class.java]

        Log.d("myLogs", "dgr view model: " + repositoryGithubUseCase)

        mainViewModel.getSomething()
    }
}
