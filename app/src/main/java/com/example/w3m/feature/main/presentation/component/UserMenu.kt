package com.example.w3m.feature.main.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserMenu(
    modifier: Modifier = Modifier,
    expandedData: Boolean,
    onDismiss: () -> Unit
) {
    MaterialTheme(
        shapes = MaterialTheme
            .shapes
            .copy(
                extraSmall = RoundedCornerShape(20.dp)
            ),
    ) {
        DropdownMenu(
            expanded = expandedData,
            onDismissRequest = onDismiss,
            modifier = modifier
                .background(Color.White)
                .fillMaxWidth(0.4f)
                .padding(4.dp),

            ) {

            DropdownMenuItem(
                text = {
                    Column {
                        Text(
                            text = "شماره تلفن",
                            fontFamily = Yekan,
                            fontSize = 12.sp,
                            color = Color(0xFF03A9F4)
                        )
                        Text(
                            text = "09376007032",
                            fontFamily = Yekan,
                            fontSize = 12.sp,
                            color = Color(0xFF03A9F4)
                        )
                    }

                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Phone,
                        contentDescription = null,
                        tint = Color(0xFF03A9F4),
                        modifier = modifier.fillMaxWidth(0.15f)
                            .aspectRatio(1f)
                    )
                },
                onClick = {

                }
            )

            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 12.dp),
                color = Color(0xFFE5E7EB)
            )

            DropdownMenuItem(
                text = {
                    Text(
                        text = "خروج",
                        fontFamily = Yekan,
                        fontSize = 12.sp,
                        color = Color.Red
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Clear,
                        contentDescription = null,
                        tint = Color.Red,
                        modifier = modifier.fillMaxWidth(0.15f)
                            .aspectRatio(1f)                    )
                },
                onClick = {

                }
            )

        }
    }

}


