package com.example.w3m.feature.users.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.R
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

@Composable
fun UserSelectionActions(modifier: Modifier = Modifier,countUser: Int) {
    Row(
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .fillMaxWidth()
            .padding(12.dp)
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {

        OutlinedButton(
            onClick = {

            },
            shape = RoundedCornerShape(14.dp),
            colors = ButtonColors(
                contentColor = Color.White,
                containerColor = Color.White,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = Color.Transparent
            ),
            border = BorderStroke(1.dp , Color(0xFFDCEAFF))
        ) {
            Icon(
                painter = painterResource(R.drawable.excel),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(0.08f),
                tint = Color.Unspecified
            )

            Spacer(
                modifier = Modifier.width(6.dp)
            )

            Text(
                text = "اکسل کاربران",
                fontFamily = Yekan,
                fontSize = 12.sp,
                color = Color.Black)
        }


        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFF1677F2)
                    )
                ) {
                    append(countUser.toString())
                }

                append(" کاربر یافت شد")
            },
            fontFamily = Yekan,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF111827),
            maxLines = 1,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End
        )

    }
}