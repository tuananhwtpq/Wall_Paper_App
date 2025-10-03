package com.example.unsplash_app.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplash_app.model.PhotoResponse
import com.example.unsplash_app.model.User
import com.example.unsplash_app.repository.HomeItemDetailRepository
import com.example.unsplash_app.state.UiState
import kotlinx.coroutines.launch

class HomeItemDetailViewModel(private val repo: HomeItemDetailRepository) : ViewModel() {

    private val _userInfo = MutableLiveData<UiState<User>>()
    val userInfo: LiveData<UiState<User>> = _userInfo

    private val _imageInfo = MutableLiveData<UiState<PhotoResponse>>()
    val imageInfo: LiveData<UiState<PhotoResponse>> = _imageInfo

    fun getPrivateProfile(username: String) {

        viewModelScope.launch {

            _userInfo.value = UiState.Loading

            val result = repo.getPrivateProfile((username))

            result.onSuccess { user ->
                _userInfo.value = UiState.Success(user)
                Log.d("HomeItemDetailViewModel", "Du lieu: ${user}")
            }
                .onFailure { error ->
                    _userInfo.value = UiState.Error(error.message ?: "Unknown Error")
                    Log.e("HomeItemDetailViewModel", "Loi: ${error.message}")
                }
        }

    }

    fun getPhotoById(id: String){
        viewModelScope.launch {
            _imageInfo.value = UiState.Loading

            val result = repo.getPhotoById(id)

            result.onSuccess {
                _imageInfo.value = UiState.Success(it)
                Log.d("HomeItemDetailViewModel", "Du lieu: ${it}")
            }
                .onFailure {
                    _imageInfo.value = UiState.Error(it.message ?: "Unknown Error")
                    Log.e("HomeItemDetailViewModel", "Loi: ${it.message}")
                }
        }
    }

}