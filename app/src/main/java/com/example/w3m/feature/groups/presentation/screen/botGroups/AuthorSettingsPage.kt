package com.example.w3m.feature.groups.presentation.screen.botGroups
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.R
import com.example.w3m.feature.groups.presentation.component.author.AuthorContentSection
import com.example.w3m.feature.groups.presentation.component.author.AuthorCostSummary
import com.example.w3m.feature.groups.presentation.component.author.AuthorScheduleSection
import com.example.w3m.feature.intro.presentation.screen.ui.theme.ColorBul
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

private val AuthorBlue = Color(0xFF3B82F6)
private val AuthorText = Color(0xFF14213D)
private val AuthorGray = Color(0xFF7B879C)
private val AuthorBorder = Color(0xFFE0E8F2)

@Composable
fun AuthorSettingsPage(
    modifier: Modifier = Modifier
) {
    var enabled by remember { mutableStateOf(false) }
    var selectedHours by remember { mutableStateOf(setOf<Int>()) }
    var textSelected by remember { mutableStateOf(true) }
    var imageSelected by remember { mutableStateOf(false) }
    val hourlyCost = when {
        textSelected && imageSelected -> 14_000
        textSelected -> 6_000
        imageSelected -> 8_000
        else -> 0
    }

    val dailyCost = selectedHours.size * hourlyCost

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier = Modifier.height(20.dp)
            )
            Text(
                text = "نویسنده هوشمند",
                fontFamily = Yekan,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0F172A)
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "تولید خودکار پست های اختصاصی",
                fontFamily = Yekan,
                fontSize = 12.sp,
                color = Color(0xFF64748B)
            )
        }
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            0.5.dp, AuthorBorder, RoundedCornerShape(22.dp)
                        ), shape = RoundedCornerShape(22.dp), colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {
                    Column {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .clickable { enabled = !enabled }
                            .padding(16.dp), verticalAlignment = Alignment.CenterVertically) {

                            Box(
                                modifier = Modifier
                                    .fillMaxSize(0.16f)
                                    .aspectRatio(1f)
                                    .background(
                                        color = Color(0xFFE5EDFA), shape = RoundedCornerShape(18.dp)
                                    ), contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.writing),
                                    contentDescription = null,
                                    tint = AuthorBlue,
                                    modifier = Modifier.fillMaxSize(0.6f)
                                )
                            }

                            Column(
                                modifier = Modifier.weight(1f).padding(start = 10.dp),
                                verticalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Text(
                                    text = "نویسنده هوشمند",
                                    fontFamily = Yekan,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = AuthorText
                                )

                                Text(
                                    text = "تولید خودکار پست‌های اختصاصی",
                                    fontFamily = Yekan,
                                    fontSize = 14.sp,
                                    color = AuthorGray
                                )
                            }


                            Switch(
                                checked = enabled,
                                onCheckedChange = { enabled = it },
                                modifier = Modifier.scale(0.75f),
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = ColorBul,
                                    uncheckedThumbColor = Color.White,
                                    uncheckedTrackColor = Color(0xFFB8C0CC),
                                    uncheckedBorderColor = Color.Transparent,
                                    checkedBorderColor = Color.Transparent
                                )
                            )

                        }
                    }

                    Text(
                        text = "زمان انتشار و نوع محتوای موردنظر خود را انتخاب کنید.",
                        fontFamily = Yekan,
                        fontSize = 14.sp,
                        color = AuthorGray,
                        modifier = Modifier.padding(
                            start = 16.dp, end = 16.dp, bottom = 16.dp
                        )
                    )

                    AnimatedVisibility(
                        visible = enabled,
                        enter = expandVertically() + fadeIn(),
                        exit = shrinkVertically() + fadeOut()
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {

                            AuthorScheduleSection(
                                selectedHours = selectedHours,
                                onHoursChange = { selectedHours = it })

                            AuthorContentSection(
                                textSelected = textSelected,
                                imageSelected = imageSelected,
                                onTextChange = { textSelected = it },
                                onImageChange = { imageSelected = it })


                            AuthorCostSummary(
                                selectedHoursCount = selectedHours.size,
                                dailyCost = dailyCost,
                                textSelected = textSelected,
                                imageSelected = imageSelected
                            )

                        }

                    }
                }
            }
        }

    }
}


