package com.example.w3m.feature.main.presentation.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.R
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Gilroy
import com.example.w3m.feature.main.presentation.bottomsheet.WalletChargeBottomSheet

@Composable
fun HeaderBalance(
    modifier: Modifier = Modifier,
    showBalance: Boolean = false
) {
    var showWalletSheet by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier, contentAlignment = Alignment.CenterStart
    ) {

        AnimatedContent(
            targetState = showBalance, transitionSpec = {

                if (targetState) {
                    (slideInHorizontally(
                        animationSpec = tween(800),
                        initialOffsetX = { -it }) + fadeIn()) togetherWith (slideOutHorizontally(
                        animationSpec = tween(800), targetOffsetX = { -it }) + fadeOut())

                } else {

                    (slideInHorizontally(
                        animationSpec = tween(800),
                        initialOffsetX = { -it }) + fadeIn()) togetherWith (slideOutHorizontally(
                        animationSpec = tween(500), targetOffsetX = { -it }) + fadeOut())
                }

            }, label = "header_balance_animation"
        ) { balanceVisible ->

            if (!balanceVisible) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        modifier = modifier
                            .padding(end = 8.dp)
                            .wrapContentWidth(),
                        textAlign = TextAlign.Center,
                        text = buildAnnotatedString {
                            append("W3M")
                            withStyle(
                                SpanStyle(
                                    color = Color(0xFF3B82F6)
                                )
                            ) {
                                append(" AI")
                            }
                        },
                        fontFamily = Gilroy,
                        color = Color(0xFF000000),
                        fontWeight = FontWeight.W900,
                        fontStyle = FontStyle.Normal,
                        fontSize = 24.sp
                    )
                }

            } else {
                var balance by remember {
                    mutableStateOf("97,647,000,000")
                }

                val balanceFontSize = when {
                    balance.length <= 13 -> 13.sp
                    balance.length <= 17 -> 11.sp
                    balance.length <= 21 -> 9.sp
                    else -> 8.sp
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .padding(8.dp)
                        .border(
                            width = 0.5.dp,
                            color = Color(0xFFDCEAFF),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clip(RoundedCornerShape(10.dp))
                        .clickable {
                            showWalletSheet = true
                        }
                        .padding(
                            horizontal = 8.dp, vertical = 6.dp
                        )) {

                    Text(
                        text = "T",
                        fontFamily = Gilroy,
                        fontSize = balanceFontSize,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        maxLines = 1,
                        softWrap = false
                    )

                    Spacer(
                        modifier = Modifier.width(3.dp)
                    )

                    Text(
                        text = balance,
                        fontFamily = Gilroy,
                        fontSize = balanceFontSize,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.width(3.dp))

                    Icon(
                        painter = painterResource(R.drawable.money),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(0.05f)
                            .aspectRatio(1f) ,
                        tint = Color(0xFFF2A900)
                    )
                }
                if (showWalletSheet) {

                    WalletChargeBottomSheet(onDismiss = {
                        showWalletSheet = false
                    }, onPayClick = {})
                }
            }
        }
    }
}
