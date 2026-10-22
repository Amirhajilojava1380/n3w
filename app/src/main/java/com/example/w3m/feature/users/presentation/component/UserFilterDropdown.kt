package com.example.w3m.feature.users.presentation.component
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

@Composable
fun UserFilterDropdown(
    title: String,
    value: String,
    icon: Int,
    options: List<String>,
    onSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color(0xFFDCE8F5),
                shape = RoundedCornerShape(14.dp)
            )
            .clickable {
                expanded = true
            }
            .padding(
                horizontal = 14.dp,
                vertical = 10.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = Color(0xFF475569)
        )

        Spacer(
            modifier = Modifier.width(8.dp)
        )

        Text(
            text = value,
            fontFamily = Yekan,
            fontSize = 14.sp,
            modifier = Modifier.weight(1f),
            color = Color(0xFF111827),

            )

        Icon(
            imageVector = Icons.Outlined.ArrowDropDown,
            contentDescription = null,
            tint = Color(0xFF111827),
        )
        MaterialTheme(
            shapes = MaterialTheme
                .shapes
                .copy(
                    extraSmall = RoundedCornerShape(20.dp)
                ),
        ) {
            DropdownMenu(
                modifier = modifier
                    .background(Color.White)
                    .fillMaxWidth(0.4f),
                expanded = expanded,
                offset = DpOffset(
                    x = (-10).dp,
                    y = 12.dp
                ),
                onDismissRequest = {
                    expanded = false
                }
            ) {

                options.forEach { option ->

                    DropdownMenuItem(
                        text = {
                            Text(
                                text = option,
                                fontFamily = Yekan,
                                fontSize = 14.sp,
                                color = Color(0xFF111827),

                                )
                        },
                        onClick = {
                            onSelected(option)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
