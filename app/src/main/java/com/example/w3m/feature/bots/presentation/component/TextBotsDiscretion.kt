package com.example.w3m.feature.bots.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

@Composable
fun TextBotsDiscretion(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        // سمت چپ
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = Color(0xFFE5E7EB)
            )

            Text(
                text = "••",
                modifier = Modifier.padding(horizontal = 8.dp),
                color = Color(0xFFB8BEC7),
                fontSize = 14.sp
            )
        }

        Text(
            text = "ربات‌ها را مدیریت کنید",
            fontFamily = Yekan,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center
        )

        // سمت راست
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "••",
                modifier = Modifier.padding(horizontal = 8.dp),
                color = Color(0xFFB8BEC7),
                fontSize = 14.sp
            )

            HorizontalDivider(
                modifier = Modifier.weight(1f),
                color = Color(0xFFE5E7EB)
            )
        }
    }
}