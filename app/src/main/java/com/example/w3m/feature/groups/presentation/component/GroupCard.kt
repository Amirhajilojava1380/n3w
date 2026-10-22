package com.example.w3m.feature.groups.presentation.component
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.example.w3m.R
import com.example.w3m.feature.groups.domain.ChatType
import com.example.w3m.feature.groups.domain.MessagingPlatform
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

private val PrimaryBlue = Color(0xFF3B82F6)
private val DarkText = Color(0xFF111827)
private val SecondaryText = Color(0xFF94A3B8)

@Composable
fun GroupCard(
    groupName: String,
    groupUsername: String,
    count:Int,
    onMenuClick: () -> Unit,
    onBotClick: (ChatType) -> Unit,
    onRulesClick: (ChatType) -> Unit,
    type:ChatType,
    platform: MessagingPlatform,
    modifier: Modifier = Modifier
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    val icon: Int = when (platform) {
        MessagingPlatform.TELEGRAM -> R.drawable.telegram
        MessagingPlatform.BALE-> R.drawable.bale
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
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color(0xFFEAF3FF)),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(icon),
                        contentDescription = "telegram",
                        modifier = Modifier.fillMaxSize(0.7f)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                // نام و آیدی گروه
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = groupName,
                        fontFamily = Yekan,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkText,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )


                    Text(
                        text = groupUsername,
                        fontFamily = Yekan,
                        fontSize = 12.sp,
                        color = SecondaryText,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    MemberCountBadge(
                        memberCount = count
                    )
                }

                TypeIcon(type = type)

            }





            HorizontalDivider(
                modifier = Modifier.padding(
                    vertical = 16.dp
                ),
                color = Color(0xFFE5E7EB)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GroupActionButton(
                    text = if (type == ChatType.GROUP)"ربات پاسخگو" else "نویسنده",
                    icon = {
                        Icon(
                            painter = painterResource(if (type == ChatType.GROUP) R.drawable.sparkles else R.drawable.writing),
                            contentDescription = null,
                            modifier = Modifier.fillMaxHeight(0.4f)
                        )
                    },
                    outlined = true,
                    modifier = Modifier.weight(1f),
                    onClick = { onBotClick(type) }
                )

                GroupActionButton(
                    text =if (type == ChatType.GROUP)"قوانین" else "بازدید" ,
                    icon = {
                        Icon(
                            painter = painterResource(if (type == ChatType.GROUP) R.drawable.gavels else R.drawable.chart),
                            contentDescription = null,
                            modifier = Modifier.fillMaxHeight(0.4f)
                        )
                    },
                    outlined = false,
                    modifier = Modifier.weight(1f),
                    onClick = { onRulesClick(type) }
                )

                // منوی سه‌نقطه
                Box (
                    modifier = Modifier,
                ){

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

                        }
                    }

                }
            }
        }
    }
}

@Composable
private fun GroupActionButton(
    text: String,
    icon: @Composable () -> Unit,
    outlined: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(14.dp)

    Row(
        modifier = modifier
            .height(40.dp)
            .clip(shape)
            .then(
                if (outlined) {
                    Modifier.border(1.0.dp, PrimaryBlue, shape)
                } else {
                    Modifier.background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFF398BFA),
                                Color(0xFF2D7FEA)
                            )
                        )
                    )
                }
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        androidx.compose.runtime.CompositionLocalProvider(
            androidx.compose.material3.LocalContentColor provides
                    if (outlined) PrimaryBlue else Color.White
        ) {
            icon()
        }


        Spacer(
            modifier = Modifier.fillMaxWidth(0.1f)
        )

        Text(
            text = text,
            modifier = Modifier.wrapContentWidth(),
            fontFamily = Yekan,
            fontSize = 14.sp,
            maxLines = 1,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Medium,
            color = if (outlined) PrimaryBlue else Color.White
        )
    }
}

