package com.example.w3m.feature.main.presentation.bottomsheet
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.R
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan
import com.example.w3m.feature.main.domain.model.WalletAmount

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalletChargeBottomSheet(
    onDismiss: () -> Unit,
    onPayClick: () -> Unit = {}
) {

    var selectedAmount by remember {
        mutableIntStateOf(1_000_000)
    }

    val amounts = listOf(
        WalletAmount(
            amount = 290_000,
            gift = "۱۰ هزار تومان هدیه",
            icon = R.drawable.gift
        ),
        WalletAmount(
            amount = 1_000_000,
            gift = "۱۰۰ هزار تومان هدیه",
            icon = R.drawable.medal
        ),
        WalletAmount(
            amount = 4_000_000,
            gift = "۸۰۰ هزار تومان هدیه",
            icon = R.drawable.diamond
        ),
        WalletAmount(
            amount = 8_000_000,
            gift = "۲ میلیون تومان هدیه",
            icon = R.drawable.king
        )
    )

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color.White,
        shape = RoundedCornerShape(
            topStart = 22.dp,
            topEnd = 22.dp
        ),
        dragHandle = null,
        sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true
        ),
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 12.dp
                )

        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.15f)
                    .padding(top = 25.dp)
            ) {

                Text(
                    text = "شارژ کیف پول",
                    modifier = Modifier.fillMaxWidth(),
                    fontFamily = Yekan,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF111827),
                    textAlign = TextAlign.Center
                )
                Spacer(
                    modifier = Modifier.height(10.dp)
                )
                Text(
                    text = "یکی از مبلغ‌های زیر را انتخاب کنید و موجودی خود را افزایش دهید.",
                    modifier = Modifier.fillMaxWidth(),
                    fontFamily = Yekan,
                    fontSize = 12.sp,
                    color = Color(0xFF6B7280),
                    textAlign = TextAlign.Center
                )

            }

            amounts.forEach { item ->

                WalletAmountItem(
                    amount = item.amount,
                    gift = item.gift,
                    icon = item.icon,
                    selected = selectedAmount == item.amount,
                    onClick = {
                        selectedAmount = item.amount
                    }
                )

            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                OutlinedButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .weight(0.8f)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(
                        0.1f.dp,
                        Color(0xFF2563EB)
                    )
                ) {

                    Text(
                        text = "بازگشت",
                        fontFamily = Yekan,
                        fontSize = 14.sp,
                        color = Color(0xFF2563EB)
                    )
                }

                Button(
                    onClick = onPayClick,
                    modifier = Modifier
                        .weight(1.8f)
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2563EB),
                        contentColor = Color.White
                    )
                ) {

                    Text(
                        text = "پرداخت ${formatPrice(selectedAmount)} تومان",
                        fontFamily = Yekan,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
private fun formatPrice(
    value: Int
): String {
    return "%,d".format(value)
}
