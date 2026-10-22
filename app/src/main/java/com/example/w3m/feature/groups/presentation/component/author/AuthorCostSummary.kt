package com.example.w3m.feature.groups.presentation.component.author


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

@Composable
fun AuthorCostSummary(
    selectedHoursCount: Int,
    dailyCost: Int,
    textSelected: Boolean,
    imageSelected: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color(0xFFEDF4FF),
                    RoundedCornerShape(16.dp)
                )
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
                Text(
                    text = "ساعت‌های انتخاب‌شده",
                    fontFamily = Yekan,
                    fontSize = 12.sp,
                    color = Color(0xFF7B879C)
                )

                Text(
                    text = "$selectedHoursCount از ۴ ساعت",
                    fontFamily = Yekan,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF14213D)
                )
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "هزینه روزانه",
                    fontFamily = Yekan,
                    fontSize = 12.sp,
                    color = Color(0xFF7B879C)
                )

                Text(
                    text = "${formatAuthorPrice(dailyCost)} تومان",
                    fontFamily = Yekan,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3B82F6)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color(0xFFEAF3FF),
                    RoundedCornerShape(12.dp)
                )
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Info,
                contentDescription = null,
                tint = Color(0xFF3B82F6)
            )

            Spacer(Modifier.size(8.dp))

            Text(
                text = "در صورت کافی بودن موجودی، پست در ساعت مشخص شده به طور خودکار ایجاد و منتشر شده و هزینه آن از حساب شما کسر خواهد شد..",
                fontFamily = Yekan,
                fontSize = 12.sp,
                color = Color(0xFF7B879C)
            )
        }

    }
}

private fun formatAuthorPrice(price: Int): String {
    return "%,d".format(price).replace(',', '٬')
}
