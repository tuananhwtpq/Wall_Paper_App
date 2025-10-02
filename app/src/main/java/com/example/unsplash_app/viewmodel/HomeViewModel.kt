package com.example.unsplash_app.viewmodel

import android.text.BoringLayout
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplash_app.api.PostApiService
import com.example.unsplash_app.api.RetrofitInstance
import com.example.unsplash_app.model.PhotoResponse
import com.example.unsplash_app.repository.HomeRepository
import com.example.unsplash_app.state.UiState
import kotlinx.coroutines.launch

class HomeViewModel(private val repo: HomeRepository) : ViewModel() {

    private val _photos = MutableLiveData<UiState<List<PhotoResponse>>>()
    val photos: LiveData<UiState<List<PhotoResponse>>> = _photos

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    init {
        fetchPhotos(1, 10)
    }

    fun fetchPhotos(page: Int, perPage: Int){
        viewModelScope.launch {
            _photos.value = UiState.Loading

            val result = repo.getAllPhotos(page, perPage)

            result.onSuccess { photoList ->
                _photos.value = UiState.Success(photoList)
                Log.d("HomeViewModel", "Du lieu: ${photoList}")
                Log.d("HomeViewModel", "Lay dl thanh cong")
            }
                .onFailure { error ->
                    _photos.value = UiState.Error(error.message ?: "Unknown Error")
                    Log.e("HomeViewModel", "Loi: ${error.message}")
                }
        }
    }

}