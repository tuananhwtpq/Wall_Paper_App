package com.example.unsplash_app.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unsplash_app.model.CollectionResponse
import com.example.unsplash_app.repository.CollectionRepository
import com.example.unsplash_app.state.UiState
import kotlinx.coroutines.launch

class CollectionViewModel(private val repo: CollectionRepository): ViewModel() {

    private val _collections = MutableLiveData<UiState<List<CollectionResponse>>>()
    val collections: LiveData<UiState<List<CollectionResponse>>> = _collections

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean> = _error

    init {
        getAllCollections(1, 20)
    }

    fun getAllCollections(page: Int, perPage: Int){
        viewModelScope.launch {
            _collections.value = UiState.Loading

            val result = repo.getAllCollections(page, perPage)

            result.onSuccess { listCollections ->
                _collections.value = UiState.Success(listCollections)
                Log.d("CollectionViewModel", "Du lieu: ${listCollections}")
                Log.d(("CollectionViewModel"), "Lay dl thanh cong")
            }
                .onFailure { isError ->
                    _collections.value = UiState.Error(isError.message ?: "Unknown Error")
                    Log.e("CollectionViewModel", "Loi: ${isError.message}")
                }
        }
    }

}