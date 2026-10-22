package com.example.w3m.feature.main.presentation.bottomsheet

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun WalletAmountItem(
    amount: Int,
    gift: String,
    selected: Boolean,
    icon: Int,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(vertical = 6.dp)
            .fillMaxWidth()
            .height(110.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White)
            .border(
                width = 1.dp,
                color = if (selected)
                    Color(0x562563EB)
                else
                    Color(0xFFE5E7EB),
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth(0.07f)
                .aspectRatio(1f)
                .border(
                    width = 1.dp,
                    color = if (selected)
                        Color(0xFF2563EB)
                    else
                        Color(0xFF9CA3AF),
                    shape = CircleShape
                )
                .padding(6.dp)
        ) {
            if (selected) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                        .background(Color(0xFF2563EB))
                )
            }
        }

        Spacer(
            modifier = Modifier.width(14.dp)
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = formatPrice(amount),
                fontFamily = Yekan,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF111827)
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(
                text = gift,
                fontFamily = Yekan,
                fontSize = 12.sp,
                color = Color(0xFF2563EB),
                fontWeight = FontWeight.Medium
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize(0.25f)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(20.dp))
                .border(
                    width = 1.dp,
                    color = if (selected)
                        Color(0x562563EB)
                    else
                        Color(0xFFE5E7EB),
                    shape = RoundedCornerShape(22.dp)
                )
                .background(Color(0xFFF1F3F6)),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(0.5f),
                tint = if (selected)
                    Color(0xFF2563EB)
                else
                    Color.LightGray
            )
        }
    }
}

private fun formatPrice(
    value: Int
): String {
    return "%,d".format(value)
}
