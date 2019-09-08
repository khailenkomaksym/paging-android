package com.test.paging.presentation.view

import android.R.color
import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.test.paging.R
import com.test.paging.domain.usecase.RepositoryGithubUseCase
import com.test.paging.presentation.base.BaseActivity
import com.test.paging.presentation.viewmodel.MainViewModel
import com.test.paging.presentation.viewmodel.MainViewModelFactory
import javax.inject.Inject
import androidx.core.graphics.drawable.DrawableCompat
import android.graphics.drawable.Drawable

class MainActivity : BaseActivity() {

    @Inject
    lateinit var repositoryGithubUseCase: RepositoryGithubUseCase

    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var mainViewModel: MainViewModel

    lateinit var editQuery: EditText
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.elevation = 0f

        mainViewModel = ViewModelProviders.of(this, MainViewModelFactory(repositoryGithubUseCase))[MainViewModel::class.java]

        editQuery = findViewById(R.id.edit_query)
        recyclerView = findViewById(R.id.recycler_view)

        setEditQueryTintColor()
    }

    //for pre-23 API devices
    private fun setEditQueryTintColor() {
        val drawableSearch = ContextCompat.getDrawable(this, R.drawable.baseline_search_black_24)
        if (drawableSearch != null) {
            val drawable = DrawableCompat.wrap(drawableSearch)
            DrawableCompat.setTint(drawable, ContextCompat.getColor(this, R.color.colorAccent))
            DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN)
            editQuery.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
        }
    }
}
