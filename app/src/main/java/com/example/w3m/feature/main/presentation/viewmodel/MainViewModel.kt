package com.example.w3m.feature.main.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.w3m.core.datastore.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel(){

    val isFirstOpen = dataStoreRepository.isFirstOpen()

}