package com.example.w3m.feature.bots.presentation.screen.botsetting
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info

import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import com.example.w3m.R

private val PrimaryBlue = Color(0xFF1677F2)
private val PageBackground = Color.White
private val TextPrimary = Color(0xFF111827)
private val TextSecondary = Color(0xFF64748B)
private val LightBlue = Color(0xFFEAF4FF)

@Composable
fun AiSettingsPage(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onAddClick: () -> Unit = {},
    onAddFileClick: () -> Unit = {},
    onTokenClick: () -> Unit = {}
) {
    var isAiEnabled by remember {
        mutableStateOf(false)
    }

    var instruction by remember {
        mutableStateOf(TextFieldValue(""))
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(PageBackground)
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 12.dp,
                    bottom = 20.dp
                ),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "تنظیمات هوش مصنوعی",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp),
                fontFamily = Yekan,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                textAlign = TextAlign.Center
            )
            Text(
                text = "پاسخهای دقیق با هوش مصنوعی",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                fontFamily = Yekan,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = TextSecondary,
                textAlign = TextAlign.Center
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 12.dp)
                .navigationBarsPadding()
        ) {
            AiResponseCard(
                enabled = isAiEnabled,
                onEnabledChange = {
                    isAiEnabled = it
                }
            )

            AnimatedVisibility(
                visible = isAiEnabled,
                enter = expandVertically(
                    expandFrom = Alignment.Top
                ) + fadeIn(),
                exit = shrinkVertically(
                    shrinkTowards = Alignment.Top
                ) + fadeOut()
            ) {
                Column {
                    Spacer(
                        modifier = Modifier.height(14.dp)
                    )

                    AiInstructionCard(
                        instruction = instruction,
                        onInstructionChange = {
                            if (it.text.length <= 500) {
                                instruction = it
                            }
                        }
                    )
                }
            }
            Spacer(
                modifier = Modifier.height(18.dp)
            )
            Column(
                modifier = modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                AiResourcesCard(
                    onAddFileClick = onAddFileClick
                )

                SubscriptionCard(
                    onTokenClick = onTokenClick
                )
            }

        }

    }

}


@Composable
fun AiResponseCard(
    enabled: Boolean,
    onEnabledChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(22.dp))
            .background(color = Color(0xFFF8FBFF))
            .border(width = 0.5.dp, color = Color(0xFFDCEAFF),shape = RoundedCornerShape(22.dp))
        ,

    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .padding(end = 18.dp)
                    .fillMaxSize(0.15f)
                    .aspectRatio(1f)
                    .background(
                        color = LightBlue,
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Face,
                    contentDescription = null,
                    tint = Color(0xFF123F85),
                    modifier = Modifier.fillMaxSize(0.6f)
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = "پاسخگویی هوشمند",
                    fontFamily = Yekan,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )

                Spacer(
                    modifier = Modifier.height(2.dp)
                )

                Text(
                    text = "پاسخگویی هوشمند ربات با استفاده ازهوش مصنوعی",
                    fontFamily = Yekan,
                    fontSize = 12.sp,
                    color = TextSecondary,
                    textAlign = TextAlign.Start
                )
            }

            Spacer(
                modifier = Modifier.width(12.dp)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {



                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Switch(
                    checked = enabled,
                    onCheckedChange = onEnabledChange,
                    modifier = Modifier.scale(0.8f),
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = PrimaryBlue,
                        uncheckedThumbColor = Color.White,
                        uncheckedTrackColor = Color(0xFF9AA9BF),
                        uncheckedBorderColor = Color.Transparent
                    )
                )

                Text(
                    text = if (enabled) "فعال" else "غیرفعال",
                    fontFamily = Yekan,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (enabled) {
                        PrimaryBlue
                    } else {
                        TextSecondary
                    }
                )
            }
        }
    }
}

@Composable
fun AiInstructionCard(
    instruction: TextFieldValue,
    onInstructionChange: (TextFieldValue) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(22.dp))
                .background(color = Color(0xFFF8FBFF))
                .border(width = 0.5.dp, color = Color(0xFFDCEAFF),shape = RoundedCornerShape(22.dp))
                .padding(20.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .padding(end = 14.dp)
                        .fillMaxSize(0.15f)
                        .aspectRatio(1f)
                        .background(
                            color = LightBlue,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = null,
                        tint = Color(0xFF123F85),
                        modifier = Modifier.fillMaxSize(0.6f)
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.Start
                ) {

                    Text(
                        text = "دستورالعمل‌های هوش مصنوعی",
                        fontFamily = Yekan,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary,
                        textAlign = TextAlign.End
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "دستورالعمل‌هایی که رفتار ربات را در وب‌سایت فروشگاه تعیین می‌کند",
                        fontFamily = Yekan,
                        fontSize = 12.sp,
                        color = TextSecondary,
                        textAlign = TextAlign.Start
                    )
                }

                Spacer(
                    modifier = Modifier.width(12.dp)
                )


            }

            Spacer(
                modifier = Modifier.height(25.dp)
            )

            OutlinedTextField(
                value = instruction,
                onValueChange = onInstructionChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                placeholder = {
                    Text(
                        text = "دستورالعمل خود را اینجا وارد کنید...",
                        fontFamily = Yekan,
                        fontSize = 12.sp,
                        color = Color(0xFF94A3B8),
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                textStyle = TextStyle(
                    fontFamily = Yekan,
                    fontSize = 12.sp,
                    color = TextPrimary,
                    textAlign = TextAlign.Start
                ),
                shape = RoundedCornerShape(18.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 8.dp
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "${instruction.text.length}/500",
                    fontFamily = Yekan,
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            // Info box
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = LightBlue,
                        shape = RoundedCornerShape(18.dp)
                    )
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize(0.15f)
                        .aspectRatio(1f)
                        .background(
                            color = PrimaryBlue,
                            shape = RoundedCornerShape(50)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.fillMaxSize(0.6f)
                    )
                }
                Spacer(
                    modifier = Modifier.width(12.dp)
                )
                Text(
                    text = "هرچه دستورالعمل‌ها دقیق‌تر باشند،پاسخ‌های بهتری ارائه خواهد شد.",
                    modifier = Modifier.weight(1f),
                    fontFamily = Yekan,
                    fontSize = 12.sp,
                    lineHeight = 24.sp,
                    color = TextPrimary,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}


@Composable
private fun AiResourcesCard(
    onAddFileClick: () -> Unit
) {
    Column (
        modifier = Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(22.dp))
            .background(color = Color(0xFFF8FBFF))
            .border(width = 0.5.dp, color = Color(0xFFDCEAFF),shape = RoundedCornerShape(22.dp))        ,
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Spacer(
                    modifier = Modifier.width(16.dp)
                )

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.Start
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Column(
                            modifier = Modifier.weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "منابع آموزشی هوش مصنوعی",
                                fontFamily = Yekan,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary,
                                textAlign = TextAlign.End
                            )

                            Spacer(
                                modifier = Modifier.height(8.dp)
                            )

                            Text(
                                text = "فایل‌ها چند دقیقه بعد از ذخیرهتنظیمات، همگام می‌شوند.",
                                fontFamily = Yekan,
                                fontSize = 12.sp,
                                color = TextSecondary,
                                textAlign = TextAlign.Center
                            )
                        }

                        Spacer(
                            modifier = Modifier.width(12.dp)
                        )



                        Box(
                            modifier = Modifier
                                .size(
                                    width = 90.dp,
                                    height = 90.dp
                                )
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFFE2E8F0),
                                    shape = RoundedCornerShape(18.dp)
                                )
                            ,
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.folder),
                                    contentDescription = "افزودن فایل",
                                    tint = PrimaryBlue,
                                    modifier = Modifier.fillMaxSize(0.2f)
                                )

                                Spacer(
                                    modifier = Modifier.height(6.dp)
                                )

                                Text(
                                    text = "افزودن فایل",
                                    fontFamily = Yekan,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = PrimaryBlue,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.13f)
                    .background(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(18.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = Color(0xFF1676F0),
                        modifier = Modifier.fillMaxSize(1f)
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    Text(
                        text = "هیچ فایلی آپلود نشده است.",
                        fontFamily = Yekan,
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                }
            }
        }
    }
}


@Composable
private fun SubscriptionCard(
    onTokenClick: () -> Unit
) {
    val LightYellow = Color(0xFFFFF5DD)
    Column(
        modifier = Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(22.dp))
            .background(color = Color(0xFFFFFCF2))
            .border(width = 0.5.dp, color = Color(0xFFFFE8A8),shape = RoundedCornerShape(22.dp))        ,
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize(0.15f)
                    .aspectRatio(1f)
                    .aspectRatio(1f)
                    .background(
                        color = LightYellow,
                        shape = RoundedCornerShape(16.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.money),
                    contentDescription = null,
                    tint = Color(0xFFF2A900),
                    modifier = Modifier.fillMaxSize(0.6f)

                )
            }

            Spacer(
                modifier = Modifier.width(16.dp)
            )

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.Start
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.Start
                    ) {

                        Text(
                            text = "فروش اشتراک",
                            fontFamily = Yekan,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Text(
                            text = "برای فعال‌سازی فروش اشتراک، ابتدا توکن پرداخت بله را ثبت کنید.",
                            fontFamily = Yekan,
                            fontSize = 12.sp,
                            lineHeight = 24.sp,
                            color = Color.Gray,
                            textAlign = TextAlign.Start
                        )
                    }

                }
            }
        }
    }
}
