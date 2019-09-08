package com.test.paging.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.paging.GlideRequests
import com.test.paging.R
import com.test.paging.data.entity.ItemsItem

class RepositoryItemViewHolder(view: View, private val glide: GlideRequests) : RecyclerView.ViewHolder(view) {
    private val textLogin: TextView = view.findViewById(R.id.text_login)
    private val textFullname: TextView = view.findViewById(R.id.text_fullname)
    private val textDate: TextView = view.findViewById(R.id.text_date)
    private val imageProfile : ImageView = view.findViewById(R.id.image_profile)
    private var repository : ItemsItem? = null

    fun bind(repository: ItemsItem?) {
        this.repository = repository

        textLogin.text = repository?.owner?.login
        textFullname.text = repository?.fullName
        textDate.text = repository?.owner?.login

        if (repository?.owner?.avatarUrl?.startsWith("http") == true) {

            imageProfile.visibility = View.VISIBLE
            glide.load(repository.owner.avatarUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_empty)
                .into(imageProfile)

        } else {

            imageProfile.visibility = View.INVISIBLE
            glide.clear(imageProfile)

        }
    }

    companion object {
        fun create(parent: ViewGroup, glide: GlideRequests): RepositoryItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_repository, parent, false)
            return RepositoryItemViewHolder(view, glide)
        }
    }
}