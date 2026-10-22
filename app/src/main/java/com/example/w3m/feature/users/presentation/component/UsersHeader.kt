package com.example.w3m.feature.users.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

private val LightBlue = Color(0xFFEAF4FF)

@Composable
fun UsersHeader(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    textHed : String = "مدیریت کاربران",
    textDirection: String = "جستجو و فیلتر کاربران گروه‌ها"
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(
                horizontal = 14.dp,
                vertical = 20.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(LightBlue),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "مدیریت کاربران",
                tint = PrimaryBlue,
                modifier = Modifier.size(30.dp)
            )
        }

        Spacer(
            modifier = Modifier.width(14.dp)
        )

        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = textHed,
                fontFamily = Yekan,
                fontSize = 20.sp,
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF111827),
                textAlign = TextAlign.Start
            )

            Text(
                text = textDirection,
                fontFamily = Yekan,
                maxLines = 1,
                fontSize = 14.sp,
                color = Color(0xFF64748B),
                textAlign = TextAlign.Start
            )
        }

    }
}
