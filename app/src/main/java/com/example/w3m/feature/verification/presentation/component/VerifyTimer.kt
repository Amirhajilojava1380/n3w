package com.example.w3m.feature.verification.presentation.component
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import android.annotation.SuppressLint
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.delay

@SuppressLint("DefaultLocale")
@Composable
fun VerifyTimer(
    modifier: Modifier = Modifier,
    duration: Int = 120,
    onFinished: () -> Unit = {},
    timerKey: Int
    ) {

    var timeLeft by rememberSaveable {
        mutableIntStateOf(duration)
    }

    LaunchedEffect(timerKey) {
        timeLeft = duration
        while (timeLeft > 0) {
            delay(1000)
            timeLeft--
        }
        onFinished()
    }

    val minute = timeLeft / 60
    val second = timeLeft % 60

    Text(
        text = String.format("%02d:%02d", minute, second),
        modifier = modifier,
        style = MaterialTheme.typography.bodyMedium,
        color = Color.Black,
        fontWeight = FontWeight.SemiBold
    )
}
