package com.example.w3m.feature.bots.presentation.component


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.CardColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.R
import com.example.w3m.feature.bots.domain.model.BotUiModel
import com.example.w3m.feature.bots.presentation.component.botsetting.BotSettingsBottomSheet
import com.example.w3m.feature.bots.presentation.component.botsetting.startmessage.BotButtonUiModel
import com.example.w3m.feature.intro.presentation.screen.ui.theme.ColorBulLight
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Gilroy
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

@Composable
fun BotCard(
    bot: BotUiModel,
    modifier: Modifier = Modifier,
    listBots: List<BotButtonUiModel>,
) {

    var expanded by remember {
        mutableStateOf(false)
    }
    var showSettingsSheet by remember {
        mutableStateOf(false)
    }
    var showToken by remember {
        mutableStateOf(false)
    }

    var token by remember {
        mutableStateOf(
            "8314669470:AAHLx5DkMSiTqevQqB-bYGL2KPiHda_kWBw"
        )
    }


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 4.dp
            ),
        shape = RoundedCornerShape(18.dp),
        colors = CardColors(
            containerColor = Color.White, contentColor = Color.White,
            disabledContainerColor = Color.White,
            disabledContentColor = Color.White
        ),
        border = BorderStroke(0.03.dp, Color.LightGray)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize(0.15f)
                        .sizeIn(
                            minWidth = 18.dp,
                            maxWidth = 72.dp,
                            minHeight = 18.dp,
                            maxHeight = 72.dp
                        )
                        .aspectRatio(1f)
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(CircleShape)
                            .background(ColorBulLight),
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            painter = painterResource(
                                R.drawable.user
                            ),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.fillMaxSize(0.6f)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize(0.4f)
                            .align(Alignment.BottomEnd)
                            .clip(CircleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center
                    ) {

                        Icon(
                            painter = painterResource(
                                R.drawable.telegram
                            ),
                            contentDescription = "Telegram",
                            tint = Color.Unspecified,
                            modifier = Modifier.fillMaxSize(0.85f)
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.fillMaxWidth(0.05f)
                )

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = bot.name,
                        fontFamily = Yekan,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Text(
                        text = bot.username,
                        fontFamily = Gilroy,
                        fontSize = 12.sp,
                        color = Color.DarkGray
                    )
                }

                Spacer(
                    modifier = Modifier.fillMaxWidth(0.02f)
                )


                Box(
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(14.dp)
                        )
                        .background(
                            if (bot.isConnected)
                                Color(0xFFE5F8EF)
                            else
                                Color(0xFFFFEEEE)
                        )
                        .padding(
                            horizontal = 8.dp,
                            vertical = 7.dp
                        )
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxSize(0.02f)
                                .aspectRatio(1f)
                                .clip(CircleShape)
                                .background(
                                    if (bot.isConnected)
                                        Color(0xFF16A34A)
                                    else
                                        Color.Red
                                )
                        )

                        Spacer(
                            modifier = Modifier.fillMaxWidth(0.03f)
                        )

                        Text(
                            text = if (bot.isConnected)
                                "متصل"
                            else
                                "قطع",
                            fontFamily = Yekan,
                            fontSize = 12.sp,
                            color = if (bot.isConnected)
                                Color(0xFF15945B)
                            else
                                Color.Red
                        )
                    }
                }


            }

            HorizontalDivider(
                modifier = Modifier.padding(
                    vertical = 16.dp
                ),
                color = Color(0xFFE5E7EB)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {


                // تنظیمات
                Button(
                    onClick = {
                        showSettingsSheet = true
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF3B82F6)
                    )
                ) {

                    Icon(
                        painter = painterResource(R.drawable.setting),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth(0.2f)
                            .aspectRatio(1f),
                        tint = Color.White
                    )

                    Spacer(
                        modifier = Modifier.fillMaxWidth(0.1f)
                    )

                    Text(
                        text = "تنظیمات",
                        fontFamily = Yekan,
                        fontSize = 14.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Normal
                    )
                }


                // توکن
                OutlinedButton(
                    onClick = { showToken = !showToken },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFF1976D2)
                    ),
                    border = BorderStroke(
                        width = 1.dp,
                        color = Color(0xFF1976D2)
                    )

                ) {

                    Icon(
                        painter = painterResource(R.drawable.key),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(0.2f),
                        tint = Color(0xFF1976D2)
                    )

                    Spacer(
                        modifier = Modifier.fillMaxWidth(0.1f)
                    )

                    Text(
                        text = "توکن",
                        fontFamily = Yekan,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF1976D2)
                    )

                }
                Box {

                    IconButton(
                        onClick = {
                            expanded = true
                        }
                    ) {

                        Icon(
                            painter = painterResource(R.drawable.more),
                            contentDescription = "منو",
                            tint = Color(0xFF6B7280),
                            modifier = Modifier.fillMaxWidth(0.5f)
                        )
                    }


                    MaterialTheme(
                        shapes = MaterialTheme
                            .shapes
                            .copy(
                                extraSmall = RoundedCornerShape(20.dp)
                            ),
                    ) {
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = {
                                expanded = false
                            },
                            modifier = Modifier
                                .background(Color.White)
                                .fillMaxWidth(0.4f)
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {

                                        Icon(
                                            painter = painterResource(R.drawable.visibility),
                                            contentDescription = null,
                                            tint = Color(0xFF03A9F4),
                                            modifier = Modifier.fillMaxWidth(0.13f)
                                        )

                                        Spacer(modifier = Modifier.fillMaxWidth(0.1f))

                                        Text(
                                            text = "مشاهده ربات",
                                            fontFamily = Yekan,
                                            fontSize = 12.sp,
                                            color = Color(0xFF03A9F4)
                                        )
                                    }
                                },
                                onClick = {

                                }
                            )

                            DropdownMenuItem(
                                text = {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {

                                        Icon(
                                            painter = painterResource(R.drawable.chart),
                                            contentDescription = null,
                                            tint = Color(0xFF03A9F4),
                                            modifier = Modifier.fillMaxWidth(0.13f)
                                        )

                                        Spacer(modifier = Modifier.fillMaxWidth(0.1f))


                                        Text(
                                            text = "وضعیت ربات",
                                            fontFamily = Yekan,
                                            fontSize = 12.sp,
                                            color = Color(0xFF03A9F4)
                                        )
                                    }
                                },
                                onClick = {

                                }
                            )

                            DropdownMenuItem(
                                text = {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {

                                        Icon(
                                            painter = painterResource(R.drawable.power),
                                            contentDescription = null,
                                            tint = Color(0xFF03A9F4),
                                            modifier = Modifier.fillMaxWidth(0.13f)
                                        )

                                        Spacer(modifier = Modifier.fillMaxWidth(0.1f))

                                        Text(
                                            text = "خاموش کردن",
                                            fontFamily = Yekan,
                                            fontSize = 12.sp,
                                            color = Color(0xFF03A9F4)
                                        )
                                    }
                                },
                                onClick = {

                                }
                            )

                            DropdownMenuItem(
                                text = {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {

                                        Icon(
                                            painter = painterResource(R.drawable.delete),
                                            contentDescription = null,
                                            tint = Color.Red,
                                            modifier = Modifier.fillMaxWidth(0.13f)
                                        )

                                        Spacer(modifier = Modifier.fillMaxWidth(0.1f))

                                        Text(
                                            text = "حذف ربات",
                                            fontFamily = Yekan,
                                            fontSize = 12.sp,
                                            color = Color.Red
                                        )
                                    }
                                },
                                onClick = {

                                }
                            )
                        }
                    }

                }
            }
            AnimatedVisibility(
                visible = showToken,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {

                    HorizontalDivider(
                        color = Color(0xFFE5E7EB)
                    )

                    Spacer(
                        modifier = Modifier.height(14.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(0.11f)
                                .aspectRatio(1f)
                                .clip(CircleShape)
                                .background(Color(0xFFE8F5FF)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(
                                    R.drawable.key
                                ),
                                contentDescription = null,
                                tint = Color.Blue,
                                modifier = Modifier
                                    .fillMaxSize(0.5f)
                                    .aspectRatio(1f)
                            )
                        }
                        Spacer(
                            modifier = Modifier.fillMaxWidth(0.04f)
                        )
                        Text(
                            text = "کلید API شما",
                            fontFamily = Yekan,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF243B64)
                        )

                    }

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )



                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(Color(0xFFF5F7FA))
                            .padding(
                                horizontal = 10.dp,
                                vertical = 8.dp
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        OutlinedTextField(
                            value = token,
                            onValueChange = {
                                token = it
                            },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            textStyle = LocalTextStyle.current.copy(
                                fontFamily = Gilroy,
                                fontSize = 12.sp
                            ),
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = Color(0xFF1976D2),
                                unfocusedBorderColor = Color(0xFFD9DEE7),
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White
                            ),
                            trailingIcon = {
                                IconButton(
                                    onClick = {

                                    },
                                    modifier = Modifier.fillMaxSize(0.07f)
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.copy),
                                        contentDescription = "کپی",
                                        modifier = Modifier.fillMaxSize(),
                                        tint = Color(0xFF243B64)
                                    )
                                }
                            }
                        )


                    }
                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xC1EEF7FD))
                            .padding(
                                horizontal = 10.dp,
                                vertical = 8.dp
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = "Info",
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxSize(0.1f),
                            tint = Color.Blue
                        )

                        Text(
                            text = "این کلید را در جای امن نگهداری کنید",
                            modifier = Modifier.weight(1f),
                            fontFamily = Gilroy,
                            fontSize = 12.sp,
                            color = Color(0xFF243B64),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )


                    }
                }
            }
            if (showSettingsSheet) {

                BotSettingsBottomSheet(
                    onDismiss = {
                        showSettingsSheet = false
                    },
                    onSave = {
                        showSettingsSheet = false

                        // بعداً ViewModel
                        // saveSettings()
                    },
                    bot = bot,
                    list = listBots
                )
            }
        }
    }
}


