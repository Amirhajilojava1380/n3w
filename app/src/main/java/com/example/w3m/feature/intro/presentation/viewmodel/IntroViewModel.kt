package com.example.w3m.feature.intro.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.w3m.core.datastore.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class IntroViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel(){

    fun finishIntro() {
        viewModelScope.launch {
            dataStoreRepository.setFirstOpen(false)
        }
    }


}