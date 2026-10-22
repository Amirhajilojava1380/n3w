package com.example.w3m.feature.groups.presentation.screen.botGroups
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.R
import com.example.w3m.feature.intro.presentation.screen.ui.theme.ColorBul
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

private val PrimaryBlue = Color(0xFF3B82F6)
private val LightBlue = Color(0xFFF8FBFF)
private val BlueBorder = Color(0xFFDCEAFF)
private val LightGold = Color(0xFFFFF9E9)
private val GoldBorder = Color(0xFFFFD45C)
private val DarkText = Color(0xFF111827)
private val SecondaryText = Color(0xFF718096)

@Composable
fun BotWorkingSettings(
    modifier: Modifier = Modifier
) {

    var mandatoryRegistration by remember { mutableStateOf(false) }
    var workingHoursEnabled by remember { mutableStateOf(false) }

    val weekdays = listOf(
        "شنبه",
        "یکشنبه",
        "دوشنبه",
        "سه‌شنبه",
        "چهارشنبه",
        "پنجشنبه"
    )

    var selectedDays by remember {
        mutableStateOf(weekdays.toSet())
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 12.dp),

    ) {
        Spacer(modifier = Modifier.height(25.dp))

        // عنوان
        Text(
            text = "قوانین گروه",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontFamily = Yekan,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF111827)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "امکان تعریف و مدیریت قوانین گروه",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontFamily = Yekan,
            fontSize = 12.sp,
            color = Color(0xFF6B7280)
        )

        Spacer(modifier = Modifier.height(18.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 0.5.dp,
                    color = BlueBorder,
                    shape = RoundedCornerShape(22.dp)
                ),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(
                containerColor = LightBlue
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize(0.16f)
                        .aspectRatio(1f)
                        .background(
                            color = Color(0xFFD6E8FF),
                            shape = RoundedCornerShape(20.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.send),
                        contentDescription = "تلگرام",
                        tint = PrimaryBlue,
                        modifier = Modifier.fillMaxSize(0.6f).align(Alignment.Center)
                    )
                }

                Column(
                    modifier = Modifier.weight(1f).padding(start = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {

                        Text(
                            text = "ثبت‌نام اجباری در ربات",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkText
                        )
                    }

                    Text(
                        text = "کاربران برای ارسال پیام در گروه\n ابتدا باید در ربات ثبت‌نام کرده باشند.",
                        fontSize = 12.sp,
                        color = SecondaryText,
                    )
                }
                Switch(
                    checked = mandatoryRegistration,
                    onCheckedChange = {
                        mandatoryRegistration = it
                    },
                    modifier = Modifier.scale(0.75f),
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = ColorBul,
                        uncheckedThumbColor = Color.White,
                        uncheckedTrackColor = Color(0xFFB8C0CC),
                        uncheckedBorderColor = Color.Transparent,
                        checkedBorderColor = Color.Transparent
                    )
                )

            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 0.5.dp,
                    color = GoldBorder,
                    shape = RoundedCornerShape(22.dp)
                ),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(
                containerColor = LightGold
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(0.16f)
                            .aspectRatio(1f)
                            .background(
                                color = Color(0xFFFFEFC4),
                                shape = RoundedCornerShape(20.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.time),
                            contentDescription = "ساعات کاری",
                            tint = Color(0xFFFFB900),
                            modifier = Modifier.fillMaxSize(0.6f)
                        )
                    }

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "فعال‌سازی ساعات کاری",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkText
                        )

                        Text(
                            text = "روزها و ساعات کاری که ربات به پیام‌های کاربران پاسخ می‌دهد را مشخص کنید.",
                            fontSize = 12.sp,
                            color = SecondaryText,
                            lineHeight = 24.sp
                        )
                    }

                    Switch(
                        checked = workingHoursEnabled,
                        onCheckedChange = {
                            workingHoursEnabled = it
                        },
                        modifier = Modifier.scale(0.75f),
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = ColorBul,
                            uncheckedThumbColor = Color.White,
                            uncheckedTrackColor = Color(0xFFB8C0CC),
                            uncheckedBorderColor = Color.Transparent,
                            checkedBorderColor = Color.Transparent
                        )
                    )
                }

                // روزهای کاری
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "روزهای کاری:",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkText
                    )

                    Row(
                        modifier = Modifier.horizontalScroll(
                            rememberScrollState()
                        ),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        weekdays.forEach { day ->
                            val selected = day in selectedDays

                            Box(
                                modifier = Modifier
                                    .background(
                                        color = Color(0xFFE2EFFF),
                                        shape = RoundedCornerShape(14.dp)
                                    )
                                    .border(
                                        width = 0.5.dp,
                                        color = Color(0xFFE2EFFF),
                                        shape = RoundedCornerShape(14.dp)
                                    )
                                    .padding(
                                        horizontal = 10.dp,
                                        vertical = 8.dp
                                    )
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Text(
                                        text = day,
                                        fontSize = 12.sp,
                                        color = if (selected) {
                                            PrimaryBlue
                                        } else {
                                            SecondaryText
                                        },
                                        fontWeight = FontWeight.Medium
                                    )

                                }
                            }
                        }
                    }
                }

                // ساعت شروع و پایان
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "ساعت کاری:",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkText
                    )


                    Row(
                        modifier = Modifier
                            .border(
                                width = 0.5.dp,
                                color = Color(0xFFF0E2C3),
                                shape = RoundedCornerShape(18.dp)
                            )
                            .padding(
                                horizontal = 12.dp,
                                vertical = 12.dp
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "09:00",
                            fontSize = 12.sp,
                            color = DarkText
                        )

                        Text(
                            text = "تا",
                            fontSize = 12.sp,
                            color = SecondaryText
                        )

                        Text(
                            text = "17:00",
                            fontSize = 12.sp,
                            color = DarkText
                        )
                    }
                }

                // پیام خارج از ساعات کاری
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFFFFF0C9),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(14.dp)
                ) {
                    Text(
                        text = "در خارج از ساعات کاری، پیام‌های ارسالی به طور خودکار حذف خواهند شد.",
                        fontSize = 12.sp,
                        color = Color(0xFF746B5B),
                    )
                }
            }
        }
    }
}

