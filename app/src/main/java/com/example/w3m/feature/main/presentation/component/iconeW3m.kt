package com.example.w3m.feature.main.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.w3m.R
import com.example.w3m.feature.main.presentation.screen.ui.theme.W3mTheme

@Composable
fun IconW3m(modifier: Modifier = Modifier) {
    Row (
        modifier = modifier.padding(horizontal = 4.dp),
        horizontalArrangement = Arrangement.Center ,
        verticalAlignment = Alignment.CenterVertically
    ){
        HeaderBalance()


        Box(
            modifier = Modifier
                .fillMaxWidth(0.14f)
                .aspectRatio(1f)
                .background(
                    color = Color.Black,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                painter = painterResource(R.drawable.logow),
                contentDescription = "logo",
                modifier = Modifier.fillMaxWidth(0.9f)
                    .aspectRatio(1f),
                tint = Color.White
            )
        }

    }
}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO, showSystemUi = true)
@Composable
fun IconW3mPreview() {
    W3mTheme {
        IconW3m()
    }
}
