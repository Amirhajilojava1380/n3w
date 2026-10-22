package com.example.w3m.feature.main.presentation.component
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.R
import com.example.w3m.feature.auth.presentation.screen.ui.theme.W3mTheme
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan
import kotlinx.coroutines.launch

@Composable
fun IconProfile(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    val scale = remember {
        Animatable(1f)
    }
    var expanded by rememberSaveable {
        mutableStateOf(false)
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .padding(end = 6.dp)
                .fillMaxWidth(0.16f)
                .aspectRatio(1f)
                .graphicsLayer {
                    scaleX = scale.value
                    scaleY = scale.value
                }
                .background(
                    color = Color.DarkGray,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                painter = painterResource(R.drawable.userr),
                contentDescription = "logo",
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .fillMaxSize(0.8f)
                    .clickable {
                        expanded = !expanded
                        scope.launch {

                            scale.animateTo(
                                targetValue = 0.80f,
                                animationSpec = tween(80)
                            )

                            scale.animateTo(
                                targetValue = 1f,
                                animationSpec = tween(120)
                            )
                        }
                    },
                tint = Color.White
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = "امیر محمد حاجیلوی",
                color = Color(0xFF000000),
                fontWeight = FontWeight.W900,
                fontFamily = Yekan,
                fontSize = 14.sp
            )


        }

        UserMenu(
            expandedData = expanded,
            onDismiss = {
                expanded = false
            }
        )

    }
}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO, showSystemUi = true)
@Composable
fun IconProfilePreview() {
    W3mTheme {
        IconProfile()
    }
}
