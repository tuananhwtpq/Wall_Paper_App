package com.example.unsplash_app.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.unsplash_app.R
import com.example.unsplash_app.adapter.HomeAdapter
import com.example.unsplash_app.api.PostApiService
import com.example.unsplash_app.api.RetrofitInstance
import com.example.unsplash_app.base.BaseFragment
import com.example.unsplash_app.databinding.FragmentHomeBinding
import com.example.unsplash_app.repository.HomeRepository
import com.example.unsplash_app.state.UiState
import com.example.unsplash_app.utils.ex.launchOnStarted
import com.example.unsplash_app.utils.ex.safeNavigate
import com.example.unsplash_app.utils.ex.showToast
import com.example.unsplash_app.viewmodel.HomeViewModel
import com.example.unsplash_app.viewmodel.HomeViewModelFactory

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private lateinit var homeAdapter: HomeAdapter
    private val homeViewModel: HomeViewModel by viewModels(){
        HomeViewModelFactory(HomeRepository(RetrofitInstance.publicApi(PostApiService::class.java)))
    }

    companion object{
        const val TAG = "Home Fragment"
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun setupView() {
        super.setupView()
        setupRecyclerView()
    }

    override fun observeData() {
        super.observeData()

        launchOnStarted {
            homeViewModel.photos.observe(viewLifecycleOwner) { state ->
                when(state){
                    is UiState.Loading -> {
                        //binding.progressBar.visibility = View.VISIBLE
                        Log.d("HomeFragment", "Dang tai du lieu")
                    }
                    is UiState.Success -> {
                        //binding.progressBar.visibility = View.GONE
                        homeAdapter.setDataList(state.data)
                }
                    is UiState.Error -> {
                        //binding.progressBar.visibility = View.GONE
                        Log.e("HomeFragment", "Loi: ${state.message}")
                    }
                }
            }
        }

    }

    override fun initListener() {
        super.initListener()
    }

    private fun setupRecyclerView() {
        homeAdapter = HomeAdapter()
        binding.rvAllItems.apply {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        homeAdapter.setOnClickItem { item, position ->
            //showToast("Item ${item?.user?.username} $position clicked")

            val bundle = bundleOf(
                "username" to item?.user?.username,
                "id" to item?.id
            )
            safeNavigate(R.id.action_mainFragment_to_homeItemDetailFragment2, bundle)
            //showToast("Di chuyen thanh cong qua Home Item Detail")
        }
    }

}