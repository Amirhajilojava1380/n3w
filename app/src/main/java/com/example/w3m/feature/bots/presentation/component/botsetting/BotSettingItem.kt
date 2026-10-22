package com.example.w3m.feature.bots.presentation.component.botsetting

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

@Composable
fun BotSettingItem(
    title: String,
    description: String,
    icon: Int,
    iconBackground: Color,
    iconTint: Color,
    actionText: String? = null,
    actionColor: Color = Color(0xFF10A66A),
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(22.dp))
            .border(
                width = 1.dp,
                color = Color(0xFFE5E7EB),
                shape = RoundedCornerShape(22.dp)
            )
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // آیکون
        Box(
            modifier = Modifier
                .fillMaxWidth(0.17f)
                .aspectRatio(1f)
                .clip(CircleShape)
                .background(iconBackground),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(0.52f),
                tint = iconTint
            )
        }

        Spacer(
            modifier = Modifier.width(14.dp)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = title,
                fontFamily = Yekan,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF111827)
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )


            Text(
                text = description,
                fontFamily = Yekan,
                fontSize = 12.sp,
                lineHeight = 20.sp,
                color = Color(0xFF6B7280)
            )
            Spacer(
                modifier = Modifier.height(8.dp)
            )


        }

        Spacer(
            modifier = Modifier.width(8.dp)
        )

        Icon(
            imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(0.1f).aspectRatio(1f),
            tint = Color(0xFF6B7280)
        )
    }
}