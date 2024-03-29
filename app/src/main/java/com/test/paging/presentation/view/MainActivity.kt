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
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.test.paging.GlideApp
import com.test.paging.data.NetworkState
import com.test.paging.data.entity.ItemsItem
import com.test.paging.presentation.adapter.RepositoryAdapter

class MainActivity : BaseActivity() {

    @Inject
    lateinit var repositoryGithubUseCase: RepositoryGithubUseCase

    lateinit var mainViewModel: MainViewModel

    lateinit var editQuery: EditText
    lateinit var recyclerView: RecyclerView
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    lateinit var adapter: RepositoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.elevation = 0f

        editQuery = findViewById(R.id.edit_query)
        recyclerView = findViewById(R.id.recycler_view)
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout)

        mainViewModel = ViewModelProviders.of(this, MainViewModelFactory(repositoryGithubUseCase, getQuery()))[MainViewModel::class.java]

        setEditQueryTintColor()
        initAdapter()
        initSwipeRefreshLayout()
        initEditQuery()
    }

    private fun initAdapter() {
        val glide = GlideApp.with(this)
        val colorHighlight = ContextCompat.getColor(this, R.color.colorAccent)
        adapter = RepositoryAdapter(glide, colorHighlight) {
            mainViewModel.retry()
        }
        adapter.setQuery(getQuery())

        recyclerView.adapter = adapter
        mainViewModel.repositoryList.observe(this, Observer<PagedList<ItemsItem>> {
            adapter.submitList(it)
        })

        mainViewModel.getNetworkState().observe(this, Observer {
            adapter.setNetworkState(it)
        })
    }

    private fun initSwipeRefreshLayout() {
        mainViewModel.getRefreshState().observe(this, Observer {
            swipeRefreshLayout.isRefreshing = it == NetworkState.LOADING
        })
        swipeRefreshLayout.setOnRefreshListener {
            mainViewModel.refresh(getQuery())
        }
    }

    private fun initEditQuery() {
        editQuery.setSelection(getQuery().length)
        editQuery.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty()) {
                    adapter.setQuery(s.toString())
                    mainViewModel.refresh(s.toString())
                }
            }

            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    private fun getQuery(): String = editQuery.text.toString()

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
