package com.example.unsplash_app.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unsplash_app.adapter.CollectionAdapter
import com.example.unsplash_app.api.PostApiService
import com.example.unsplash_app.api.RetrofitInstance
import com.example.unsplash_app.base.BaseFragment
import com.example.unsplash_app.databinding.FragmentCollectionsBinding
import com.example.unsplash_app.repository.CollectionRepository
import com.example.unsplash_app.repository.HomeRepository
import com.example.unsplash_app.state.UiState
import com.example.unsplash_app.utils.ex.showToast
import com.example.unsplash_app.viewmodel.CollectionViewModel
import com.example.unsplash_app.viewmodel.CollectionViewModelFactory
import com.example.unsplash_app.viewmodel.HomeViewModelFactory

class CollectionsFragment : BaseFragment<FragmentCollectionsBinding>() {

    private val collectionViewModel: CollectionViewModel by viewModels() {
        CollectionViewModelFactory(CollectionRepository(RetrofitInstance.publicApi(PostApiService::class.java)))
    }

    private lateinit var collectionAdapter: CollectionAdapter

    companion object {
        const val TAG = "Collections Fragment"
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCollectionsBinding {
        return FragmentCollectionsBinding.inflate(inflater, container, false)
    }

    override fun setupView() {
        super.setupView()
        setupRecyclerView()
    }

    override fun observeData() {
        super.observeData()

        collectionViewModel.collections.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Loading -> {
                    //Hien thi loading
                }

                is UiState.Success -> {
                    collectionAdapter.setDataList(state.data)
                }

                is UiState.Error -> {
                    Log.e(TAG, "Error: ${state.message}")
                }
            }

        }
    }

    override fun initListener() {
        super.initListener()
    }


    private fun setupRecyclerView() {
        collectionAdapter = CollectionAdapter()
        binding.rvAllItems.apply {
            adapter = collectionAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        collectionAdapter.setOnClickItem { item, position ->
            showToast("Item ${item?.user?.name} at position $position clicked")
        }

    }

}