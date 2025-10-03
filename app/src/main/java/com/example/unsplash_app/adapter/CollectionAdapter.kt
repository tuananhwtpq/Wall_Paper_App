package com.example.unsplash_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.unsplash_app.base.BaseAdapterRecyclerView
import com.example.unsplash_app.databinding.CollectionItemBinding
import com.example.unsplash_app.model.CollectionResponse

class CollectionAdapter : BaseAdapterRecyclerView<CollectionResponse, CollectionItemBinding>() {
    override fun inflateBinding(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): CollectionItemBinding {
        return  CollectionItemBinding.inflate(inflater, parent, false)
    }

    override fun bindData(
        binding: CollectionItemBinding,
        item: CollectionResponse,
        position: Int
    ) {
        Glide.with(binding.root)
            .load(item.user.profile_image?.medium)
            .placeholder(android.R.drawable.ic_menu_report_image)
            .error(android.R.drawable.ic_menu_report_image)
            .into(binding.ivUser)

        binding.tvUserName.text = item.user.name
        binding.totalPhoto.text = item.total_photos.toString() + " Photos"
        binding.imageTitle.text = item.title

        Glide.with(binding.root)
            .load(item.cover_photo?.urls?.full)
            .placeholder(android.R.drawable.ic_menu_report_image)
            .error(android.R.drawable.ic_menu_report_image)
            .into(binding.ivUserImage)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}