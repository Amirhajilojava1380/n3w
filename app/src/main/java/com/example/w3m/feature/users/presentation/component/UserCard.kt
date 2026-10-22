package com.example.w3m.feature.users.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.R
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan
import com.example.w3m.feature.users.domain.model.UserUiModel

val PrimaryBlue = Color(0xFF1677F2)

@Composable
fun UserCard(
    modifier: Modifier = Modifier,
    user: UserUiModel,
    onTelegramClick: () -> Unit = {},
    onShowConnectionSheet: () -> Unit = {},

) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(Color.White)
            .clickable{
                onShowConnectionSheet()
            }
            .padding(12.dp)
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize(0.15f)
                .aspectRatio(1f)
                .clip(CircleShape)
                .background(Color(0xFFE9EDF2)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.userr),
                contentDescription = "تصویر کاربر",
                tint = Color(0xFF7B818A),
                modifier = Modifier.fillMaxSize(0.7f)
            )
        }

        Spacer(
            modifier = Modifier.width(14.dp)
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = user.name,
                fontFamily = Yekan,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF111827)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Icon(
                    painter = painterResource(R.drawable.phone),
                    contentDescription = null,
                    tint = PrimaryBlue,
                    modifier = Modifier.fillMaxWidth(0.075f)
                )
                Spacer(
                    modifier = Modifier.width(6.dp)
                )

                Text(
                    text = user.phone,
                    fontFamily = Yekan,
                    fontSize = 14.sp,
                    maxLines = 1,
                    color = PrimaryBlue,
                    modifier = Modifier.wrapContentHeight(),
                    textAlign = TextAlign.Center
                )

            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center

            ) {

                Icon(
                    painter = painterResource(R.drawable.telegrams),
                    contentDescription = null,
                    tint = PrimaryBlue,
                    modifier = Modifier.fillMaxWidth(0.075f)
                )

                Spacer(
                    modifier = Modifier.width(6.dp)
                )

                Text(
                    text = user.telegramUsername,
                    fontFamily = Yekan,
                    fontSize = 14.sp,
                    color = PrimaryBlue
                )
            }
        }


            Icon(
                painter = painterResource(R.drawable.send),
                contentDescription = null,
                tint = PrimaryBlue,
                modifier = Modifier.padding(6.dp).fillMaxWidth(0.09f)
                    .graphicsLayer {
                        rotationZ = 225f
                    }
            )


    }
}