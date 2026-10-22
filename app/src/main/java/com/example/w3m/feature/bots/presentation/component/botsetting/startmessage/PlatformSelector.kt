package com.example.w3m.feature.bots.presentation.component.botsetting.startmessage

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.R
import com.example.w3m.feature.intro.presentation.screen.ui.theme.ColorBul
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

enum class BotPlatform {
    TELEGRAM,
    BALE,
    WEB
}

@Composable
fun PlatformSelector(
    selectedPlatform: BotPlatform,
    onPlatformSelected: (BotPlatform) -> Unit,
    modifier: Modifier = Modifier
) {

        Text(
            text = "نوع پلتفرم",
            fontFamily = Yekan,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(1.dp))

        Text(
            text = "برای هر پلتفرم یک ربات انتخاب کنید",
            fontFamily = Yekan,
            fontSize = 12.sp,
            color = Color(0xFF64748B),
            maxLines = 1

        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                2.dp
            )
        ) {

            PlatformCard(
                platform = BotPlatform.BALE,
                title = "بله",
                description = "هیچ‌کدام",
                subtitle = "بدون ربات",
                icon = R.drawable.bale,
                selected = selectedPlatform == BotPlatform.BALE,
                onClick = {
                    onPlatformSelected(BotPlatform.BALE)
                },
                modifier = Modifier.weight(1f)
            )

            PlatformCard(
                platform = BotPlatform.TELEGRAM,
                title = "تلگرام",
                description = "هیچ‌کدام",
                subtitle = "بدون ربات",
                icon = R.drawable.telegram,
                selected = selectedPlatform == BotPlatform.TELEGRAM,
                onClick = {
                    onPlatformSelected(BotPlatform.TELEGRAM)
                },
                modifier = Modifier.weight(1f)
            )

        }
        Spacer(modifier = Modifier.height(10.dp))


}


@Composable
private fun PlatformCard(
    platform: BotPlatform,
    title: String,
    description: String,
    subtitle: String,
    icon: Int,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    val borderColor =
        if (selected) {
            ColorBul
        } else {
            Color(0xFFE2E8F0)
        }

    Card(
        modifier = modifier
            .padding(4.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(
            size = 22.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        border = BorderStroke(
            width = 1.dp,
            color = borderColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .padding(3.dp)
                    .fillMaxSize(0.18f)
                    .aspectRatio(1f)
                    .background(
                        color = if (selected) {
                            Color.LightGray
                        } else {
                            Color.White
                        },
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = title,
                    modifier = Modifier.fillMaxSize(0.9f)
                        .aspectRatio(1f),
                    tint = Color.Unspecified
                )
            }
            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = title,
                    fontFamily = Yekan,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )

                Text(
                    text = description,
                    fontFamily = Yekan,
                    fontSize = 12.sp,
                    color = Color(0xFF475569),
                    maxLines = 1
                )

                Text(
                    text = subtitle,
                    fontFamily = Yekan,
                    fontSize = 12.sp,
                    maxLines = 1,
                    color = Color(0xFF64748B)
                )
            }

            Spacer(
                modifier = Modifier
                    .height(4.dp)
            )


            RadioButton(
                selected = selected,
                onClick = onClick,
                colors = RadioButtonColors(
                    selectedColor = ColorBul,
                    unselectedColor = Color.LightGray,
                    disabledSelectedColor = Color.LightGray,
                    disabledUnselectedColor = Color.LightGray
                )
            )
        }
    }


}