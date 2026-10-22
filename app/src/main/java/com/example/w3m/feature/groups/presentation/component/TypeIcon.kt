package com.example.w3m.feature.groups.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
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
import com.example.w3m.R
import com.example.w3m.feature.groups.domain.ChatType
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan


@Composable
fun TypeIcon(
    type: ChatType,
    modifier: Modifier = Modifier
) {
    val title = when (type) {
        ChatType.GROUP -> "گروه"
        ChatType.CHANNEL -> "چنل"
    }


    Column(
        modifier = modifier
            .fillMaxWidth(0.15f)
            .aspectRatio(1f)
            .sizeIn(
                minWidth = 18.dp,
                maxWidth = 18.dp,
                minHeight = 18.dp,
                maxHeight = 72.dp
            )
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFFEAF3FF))
            .padding(8.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = title,
            fontFamily = Yekan,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF64748B)
        )
    }
}
