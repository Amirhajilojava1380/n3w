package com.example.w3m.feature.auth.data.sms

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import com.google.android.gms.common.api.Status
import com.google.android.gms.auth.api.phone.SmsRetriever
import com.google.android.gms.common.api.CommonStatusCodes

class SmsBroadcastReceiver : BroadcastReceiver() {

    var onOtpReceived: ((String) -> Unit)? = null

    override fun onReceive(
        context: Context?,
        intent: Intent?
    ) {

        if (intent?.action != SmsRetriever.SMS_RETRIEVED_ACTION)
            return

        val extras = intent.extras ?: return

        val status = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            extras.getParcelable(SmsRetriever.EXTRA_STATUS, Status::class.java)
        } else {
            @Suppress("DEPRECATION")
            extras.getParcelable(SmsRetriever.EXTRA_STATUS)
        }
        when (status?.statusCode) {

            CommonStatusCodes.SUCCESS -> {

                val message =
                    extras.getString(SmsRetriever.EXTRA_SMS_MESSAGE)
                        ?: return

                val otp = Regex("\\d{5}")
                    .find(message)
                    ?.value

                if (otp != null) {
                    onOtpReceived?.invoke(otp)
                }
            }

            CommonStatusCodes.TIMEOUT -> {

            }
        }
    }

}