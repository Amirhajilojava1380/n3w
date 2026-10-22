package com.example.w3m.feature.main.presentation.component
import androidx.compose.runtime.getValue

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.w3m.R

@Composable
fun W3mLoadingScreen(
    modifier: Modifier = Modifier
) {

    val infiniteTransition = rememberInfiniteTransition(
        label = "loading"
    )

    val rotation    by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 300f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                100000,
                easing = LinearEasing
            )
        ),
        label = "rotation"
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        CircularProgressIndicator(
            modifier = Modifier
                .size(54.dp)
                .graphicsLayer {
                    rotationZ = rotation
                },
            color = Color.Black,
            strokeWidth = 2.dp
        )

        Icon(
            painter = painterResource(R.drawable.logow),
            contentDescription = "W3M",
            modifier = Modifier.size(45.dp),
            tint = Color.Black
        )
    }
}
