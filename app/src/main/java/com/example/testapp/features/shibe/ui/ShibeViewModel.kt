package com.example.testapp.features.shibe.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.core.model.Event
import com.example.testapp.core.model.network.ErrorType
import com.example.testapp.core.model.network.ResponseState
import com.example.testapp.features.shibe.repository.ShibeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShibeViewModel(
    private val shibeRepository: ShibeRepository
) : ViewModel() {

    private val shibeLiveData = MutableLiveData<Event<List<String>, ErrorType>>()
    fun shibeLiveData(): LiveData<Event<List<String>, ErrorType>> = shibeLiveData


    init { load() }

    fun load() = viewModelScope.launch(Dispatchers.IO) {
        shibeLiveData.postValue(Event.Loading())
        val response = shibeRepository.loadShibeImages()

        when (response) {
            is ResponseState.Success -> {
                shibeLiveData.postValue(Event.Success(response.result))
            }

            is ResponseState.Error -> {
                shibeLiveData.postValue(Event.Error(response.errorType))
            }
        }
    }

}