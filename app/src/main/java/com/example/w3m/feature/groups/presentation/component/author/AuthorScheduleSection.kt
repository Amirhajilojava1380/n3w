package com.example.w3m.feature.groups.presentation.component.author

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.R
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

@Composable
fun AuthorScheduleSection(
    selectedHours: Set<Int>,
    onHoursChange: (Set<Int>) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)

        ,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(R.drawable.time),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(0.12f),
                tint = Color(0xFF3B82F6)
            )

            Column(
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Text(
                    text = "ساعت انتشار پست",
                    fontFamily = Yekan,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "حداکثر ۴ ساعت انتخاب کنید.",
                    fontFamily = Yekan,
                    fontSize = 12.sp,
                    color = Color(0xFF7B879C)
                )
            }
        }

        (0..23).toList().chunked(4).forEach { rowHours ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                rowHours.forEach { hour ->
                    val selected = hour in selectedHours

                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .border(
                                width = 0.5.dp,
                                color = if (selected) {
                                    Color(0xFF3B82F6)
                                } else {
                                    Color(0xFFE0E8F2)
                                },
                                shape = RoundedCornerShape(14.dp)
                            )
                            .background(
                                color = if (selected) {
                                    Color(0xFFE8F1FF)
                                } else {
                                    Color(0xFFF7F9FC)
                                },
                                shape = RoundedCornerShape(14.dp)
                            )
                            .clickable {
                                onHoursChange(
                                    when {
                                        selected -> selectedHours - hour
                                        selectedHours.size < 4 ->
                                            selectedHours + hour
                                        else -> selectedHours
                                    }
                                )
                            }
                            .padding(horizontal = 2.dp, vertical = 1.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Checkbox(
                            checked = selected,
                            onCheckedChange = { checked ->
                                onHoursChange(
                                    when {
                                        checked && selectedHours.size < 4 ->
                                            selectedHours + hour
                                        checked -> selectedHours
                                        else -> selectedHours - hour
                                    }
                                )
                            },
                            modifier = Modifier.fillMaxSize(0.4f).scale(0.8f),
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color(0xFF3B82F6)
                            )
                        )

                        Text(
                            text = "%02d:00".format(hour),
                            fontFamily = Yekan,
                            fontSize = 12.sp,
                            color = Color(0xFF14213D),
                        )
                    }
                }
            }
        }

        if (selectedHours.size == 4) {
            Text(
                text = "حداکثر ۴ ساعت انتخاب شده است.",
                fontFamily = Yekan,
                fontSize = 12.sp,
                color = Color(0xFF3B82F6)
            )
        }
    }
}
