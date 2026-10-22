package com.example.w3m.feature.auth.data.sms

import android.content.Context
import com.google.android.gms.auth.api.phone.SmsRetriever
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SmsRetrieverManager @Inject constructor(
    @ApplicationContext
    private val context: Context
) {

    fun startListening(
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {

        val client = SmsRetriever.getClient(context)

        client.startSmsRetriever()
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                onFailure(it)
            }
    }
}