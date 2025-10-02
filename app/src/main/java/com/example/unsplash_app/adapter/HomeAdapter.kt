package com.example.unsplash_app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.unsplash_app.base.BaseAdapterRecyclerView
import com.example.unsplash_app.databinding.HomeItemBinding
import com.example.unsplash_app.model.PhotoResponse

class HomeAdapter : BaseAdapterRecyclerView<PhotoResponse, HomeItemBinding>() {
    override fun inflateBinding(
        inflater: LayoutInflater,
        parent: ViewGroup
    ): HomeItemBinding {
        return HomeItemBinding.inflate(inflater, parent, false)
    }

    override fun bindData(
        binding: HomeItemBinding,
        item: PhotoResponse,
        position: Int
    ) {

        Glide.with(binding.root)
            .load(item.urls?.regular)
            .placeholder(android.R.drawable.ic_menu_report_image)
            .error(android.R.drawable.ic_menu_report_image)
            .into(binding.ivUserImage)

        binding.tvUserName.text = item.user?.name

        Glide.with(binding.root)
            .load(item.user?.profile_image?.medium)
            .placeholder(android.R.drawable.ic_menu_report_image)
            .error(android.R.drawable.ic_menu_report_image)
            .into(binding.ivUser)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}