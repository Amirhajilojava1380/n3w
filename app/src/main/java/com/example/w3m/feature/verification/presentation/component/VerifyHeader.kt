package com.example.w3m.feature.verification.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.R
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

@Composable
fun VerifyHeader(
    phone: String,
    modifier: Modifier = Modifier,
    onChengPhon: () -> Unit = {},
    onFinish: () -> Unit = {},
    timerKey: Int
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = Modifier
                .fillMaxSize(0.5f)
                .background(
                    color = Color.Black,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.logow),
                contentDescription = "logo",
                modifier = Modifier.fillMaxSize(),
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "تأیید شماره موبایل",
            fontFamily = Yekan,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color(0xFF111827)
        )

        Text(
            text = buildAnnotatedString {
                append(" کد به شماره ")
                withStyle(
                    SpanStyle(
                        color = Color(0xFF3B82F6)
                    )
                ) {
                    append(phone)
                }
                append(" ارسال شد ")
            },
            fontFamily = Yekan,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            color = Color(0xFF6B7280)
        )

        Row {
            Text(
                text = "زمان باقی مانده : ",
                fontFamily = Yekan,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = Color(0xFF6B7280)
            )
            Spacer(modifier = Modifier.width(6.dp))
            VerifyTimer(
                onFinished = {
                    onFinish()
                },
                timerKey = timerKey
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = " ویرایش شماره ",
                fontFamily = Yekan,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color(0xFF3B82F6),
                modifier = modifier.clickable {
                    onChengPhon()
                }
            )
        }
    }

}
