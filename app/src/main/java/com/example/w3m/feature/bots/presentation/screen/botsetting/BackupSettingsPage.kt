package com.example.w3m.feature.bots.presentation.screen.botsetting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.R
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

private val PrimaryBlue = Color(0xFF1677F2)
private val TextPrimary = Color(0xFF111827)
private val TextSecondary = Color(0xFF64748B)

@Composable
fun BackupSettingsPage(
    modifier: Modifier = Modifier,
    onDownloadClick: () -> Unit = {},
    onSelectFileClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Column(
            modifier = modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            Text(
                text = "پشتیبان گیری و بازیابی تنظیمات",
                fontFamily = Yekan,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0F172A)
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "فایل پشتیبان ایجادشده برای تنظیمات سایر ربات ها نیز سازگار است",
                fontFamily = Yekan,
                fontSize = 12.sp,
                color = Color(0xFF64748B)
            )
        }

        BackupCard(
            onDownloadClick = onDownloadClick
        )

        RestoreCard(
            onSelectFileClick = onSelectFileClick
        )
    }
}

@Composable
private fun BackupCard(
    onDownloadClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF8FBFF)
        ),
        border = androidx.compose.foundation.BorderStroke(
            width = 0.5.dp,
            color = Color(0xFFDCEAFF)
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                // آیکن سمت راست
                Box(
                    modifier = Modifier
                        .fillMaxSize(0.16f)
                        .aspectRatio(1f)
                        .background(
                            color = Color(0xFFDCEAFF),
                            shape = RoundedCornerShape(18.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.download),
                        contentDescription = null,
                        tint = PrimaryBlue,
                        modifier = Modifier.fillMaxSize(0.6f)
                    )
                }

                Spacer(
                    modifier = Modifier.width(16.dp)
                )
                // متن سمت چپ
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "دریافت نسخه پشتیبان",
                        fontFamily = Yekan,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary,
                        textAlign = TextAlign.Start
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Text(
                        text = "تمامی تنظیمات، دکمه‌ها و پیکربندی‌های\nربات را در قالب یک فایل ذخیره کنید.",
                        fontFamily = Yekan,
                        fontSize = 12.sp,
                        color = TextSecondary,
                        textAlign = TextAlign.Start
                    )
                }

            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Button(
                onClick = onDownloadClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.15f),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF181818)
                ),
            ) {

                Text(
                    text = "دانلود فایل تنظیمات",
                    fontFamily = Yekan,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
private fun RestoreCard(
    onSelectFileClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFCF2)
        ),
        border = androidx.compose.foundation.BorderStroke(
            width = 0.5.dp,
            color = Color(0xFFFFE8A8)
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize(0.16f)
                        .aspectRatio(1f)
                        .background(
                            color = Color(0xFFFFF0C7),
                            shape = RoundedCornerShape(18.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.folder),
                        contentDescription = null,
                        tint = Color(0xFFF5B400),
                        modifier = Modifier.fillMaxSize(0.6f)
                    )
                }

                Spacer(
                    modifier = Modifier.width(16.dp)
                )
                // متن سمت چپ
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "بازیابی تنظیمات",
                        fontFamily = Yekan,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary,
                        textAlign = TextAlign.Start
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Text(
                        text = "فایل پشتیبان را انتخاب کنید تا تنظیمات\nذخیره‌شده روی ربات اعمال شوند.",
                        fontFamily = Yekan,
                        fontSize = 12.sp,
                        color = TextSecondary,
                        textAlign = TextAlign.Start
                    )
                }

            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Button(
                onClick = onSelectFileClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.34f),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFFBE00)
                )
            ) {
                Text(
                    text = "انتخاب فایل و بارگذاری",
                    fontFamily = Yekan,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}
