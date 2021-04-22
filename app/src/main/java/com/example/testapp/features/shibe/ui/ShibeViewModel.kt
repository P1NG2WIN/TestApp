package com.example.testapp.features.shibe.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.core.model.Event
import com.example.testapp.core.model.network.ErrorType
import com.example.testapp.core.model.network.ResponseState
import com.example.testapp.features.shibe.repo.ShibeRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShibeViewModel(
    private val shibeRepo: ShibeRepo
) : ViewModel() {

    private val shibeData = MutableLiveData<Event<List<String>, ErrorType>>()
    fun shibeLiveData(): LiveData<Event<List<String>, ErrorType>> = shibeData


    init {
        load()
    }

    fun load() = viewModelScope.launch(Dispatchers.IO) {
        shibeData.postValue(Event.Loading())
        val response = shibeRepo.loadShibeImages()

        when (response) {
            is ResponseState.Success -> {
                shibeData.postValue(Event.Success(response.result))
            }

            is ResponseState.Error -> {
                shibeData.postValue(Event.Error(response.errorType))
            }
        }
    }

}