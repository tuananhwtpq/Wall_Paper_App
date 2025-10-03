package com.example.unsplash_app.ui.home_item_detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.unsplash_app.R
import com.example.unsplash_app.api.PostApiService
import com.example.unsplash_app.api.RetrofitInstance
import com.example.unsplash_app.base.BaseFragment
import com.example.unsplash_app.databinding.FragmentHomeItemDetailBinding
import com.example.unsplash_app.model.User
import com.example.unsplash_app.repository.HomeItemDetailRepository
import com.example.unsplash_app.state.UiState
import com.example.unsplash_app.utils.ex.showToast
import com.example.unsplash_app.viewmodel.HomeItemDetailViewModel
import com.example.unsplash_app.viewmodel.HomeItemDetailViewModelFactory

class HomeItemDetailFragment : BaseFragment<FragmentHomeItemDetailBinding>() {

    companion object {
        const val TAG = "HomeItemDetailFragment"
    }

    private val args: HomeItemDetailFragmentArgs by navArgs()

    private val viewModel: HomeItemDetailViewModel by viewModels() {
        HomeItemDetailViewModelFactory(
            HomeItemDetailRepository(
                RetrofitInstance.publicApi(
                    PostApiService::class.java
                )
            )
        )
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeItemDetailBinding {
        return FragmentHomeItemDetailBinding.inflate(inflater, container, false)
    }

    override fun setupView() {
        super.setupView()
        getDataFromArgs()

    }

    override fun observeData() {
        super.observeData()

        viewModel.userInfo.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Error -> {
                    Log.e("HomeItemDetailFragment", "Loi: ${state.message}")
                }

                is UiState.Loading -> {
                    Log.d("HomeItemDetailFragment", "Dang tai du lieu")
                }

                is UiState.Success -> {
                    setupDataUser(state.data)
                }
            }

        }

        viewModel.imageInfo.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Error -> {
                    Log.e("HomeItemDetailFragment", "Loi: ${state.message}")
                }

                is UiState.Loading -> {
                    Log.d("HomeItemDetailFragment", "Dang tai du lieu")

                }

                is UiState.Success -> {
                    setupImageInfo()
                    Log.d("HomeItemDetailFragment", "Du lieu: ${state.data}")
                }
            }
        }

    }

    override fun initListener() {
        super.initListener()

        binding.backBtn.setOnClickListener { view ->
            view.post { findNavController().popBackStack() }

        }
    }

    override fun onBack() {
        super.onBack()
    }

    private fun getDataFromArgs() {
        val username = args.username

        if (!username.isNullOrEmpty()) {
            viewModel.getPrivateProfile(username)
        } else {
            Log.e(TAG, "UserName is null or empty")
        }
    }

    private fun setupDataUser(user: User) {
        Glide.with(binding.root)
            .load(user.profile_image?.medium)
            .placeholder(android.R.drawable.ic_menu_report_image)
            .error(android.R.drawable.ic_menu_report_image)
            .into(binding.ivUserAvatar)

        binding.tvUserName.text = user.name
        binding.totalLikes.text = user.total_likes.toString()
        binding.totalDownload.text = user.downloads.toString()

        binding.tvAddress.text = user.location.toString()
    }


    private fun setupImageInfo(){

    }
}