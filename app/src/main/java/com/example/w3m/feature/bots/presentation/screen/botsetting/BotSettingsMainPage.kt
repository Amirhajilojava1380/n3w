package com.example.w3m.feature.bots.presentation.screen.botsetting


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.R
import com.example.w3m.feature.bots.presentation.component.botsetting.BotSettingItem
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

@Composable
fun BotSettingsMainPage(
    onStartMessageClick: () -> Unit,
    onButtonsClick: () -> Unit,
    onSmartReplyClick: () -> Unit,
    onBackupClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 12.dp)
    ) {

        Spacer(modifier = Modifier.height(25.dp))

        // عنوان
        Text(
            text = "تنظیمات ربات",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontFamily = Yekan,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF111827)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "ربات خود را مدیریت و سفارشی‌سازی کنید",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontFamily = Yekan,
            fontSize = 12.sp,
            color = Color(0xFF6B7280)
        )

        Spacer(modifier = Modifier.height(18.dp))

        BotSettingItem(
            title = "پیام شروع",
            description = "عنوان، توضیحات، آیکون و ربات ورود",
            icon = R.drawable.chat,
            iconBackground = Color(0xFF2563EB),
            iconTint = Color.White,
            onClick = onStartMessageClick
        )

        Spacer(modifier = Modifier.height(14.dp))

        BotSettingItem(
            title = "مدیریت دکمه‌ها",
            description = "هماهنگ با تمام بخش‌ها",
            icon = R.drawable.dashboard,
            iconBackground = Color(0xFF2563EB),
            iconTint = Color.White,
            actionText = "افزودن دکمه",
            actionColor = Color(0xFF10A66A),
            onClick = onButtonsClick
        )

        Spacer(modifier = Modifier.height(14.dp))

        BotSettingItem(
            title = "پاسخگویی هوشمند",
            description = "پاسخ‌های دقیق با هوش مصنوعی",
            icon = R.drawable.bot,
            iconBackground = Color(0xFF2563EB),
            iconTint = Color.White,
            actionText = "آماده استفاده",
            actionColor = Color(0xFF10A66A),
            onClick = onSmartReplyClick
        )

        Spacer(modifier = Modifier.height(14.dp))

        BotSettingItem(
            title = "پشتیبان‌گیری و بازیابی تنظیمات",
            description = "پشتیبان ایجادشده برای تنظیمات\nسایر ربات‌ها نیز قابل استفاده است",
            icon = R.drawable.magic,
            iconBackground = Color(0xFF2563EB),
            iconTint = Color.White,
            onClick = onBackupClick
        )

        Spacer(modifier = Modifier.height(14.dp))
    }
}