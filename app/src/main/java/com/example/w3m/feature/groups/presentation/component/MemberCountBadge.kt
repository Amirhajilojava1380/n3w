package com.example.w3m.feature.groups.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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

@Composable
fun MemberCountBadge(
    memberCount: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .wrapContentWidth()
            .height(20.dp)
            .background(
                color = Color(0xFFE5F7EF),
                shape = RoundedCornerShape(80)
            )

        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.userr),
            contentDescription = "تعداد اعضا",
            tint = Color(0xFF27865D),
            modifier = Modifier.fillMaxHeight(0.6f).padding(start = 4.dp)

        )

        Text(
            text = " عضو  $memberCount ",
            modifier = Modifier.wrapContentWidth().padding(end = 4.dp),
            fontFamily = Yekan,
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF27865D)
        )
    }
}
