package com.example.w3m.feature.bots.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.R
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

@Composable
fun FirstConnectionScreen(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // عکس اصلی
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {

            Image(
                painter = painterResource(
                    id = R.drawable.robotsvg
                ),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(310.dp),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(
            modifier = Modifier.height(14.dp)
        )

        Text(
            text = "اولین پلتفرم را متصل کنید",
            fontFamily = Yekan,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF111827)
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Text(
            text = "برای شروع، روی دکمه « + » بزنید.",
            fontFamily = Yekan,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF6B7280)
        )

        Spacer(
            modifier = Modifier.height(14.dp)
        )

        // کارت توضیحات
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF9FAFB)
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 1.dp
            )
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 18.dp,
                        vertical = 16.dp
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = null,
                    modifier = Modifier.size(14.dp),
                    tint = Color(0xFF2563EB)
                )

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "چرا اتصال لازم است؟",
                        fontFamily = Yekan,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF111827)
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )

                    Text(
                        text = "با اتصال پلتفرم، امکانات بیشتر و تجربه‌ی شخصی‌سازی‌شده خواهید داشت.",
                        fontFamily = Yekan,
                        fontSize = 14.sp,
                        lineHeight = 24.sp,
                        color = Color(0xFF6B7280)
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier.height(6.dp)
        )
    }
}