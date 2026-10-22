package com.example.w3m.feature.verification.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.w3m.core.datastore.DataStoreRepository
import com.example.w3m.feature.auth.data.sms.SmsRetrieverManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VerifyViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
    private val smsRetrieverManager: SmsRetrieverManager
) : ViewModel() {
    fun startSmsListener() {

        smsRetrieverManager.startListening(

            onSuccess = {
                // شروع گوش دادن به پیامک
            },

            onFailure = {
                // هندل خطا
            })

    }
}