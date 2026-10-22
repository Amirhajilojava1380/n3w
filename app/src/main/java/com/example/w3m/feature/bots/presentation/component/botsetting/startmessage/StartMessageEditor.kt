package com.example.w3m.feature.bots.presentation.component.botsetting.startmessage

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign.Companion.Right
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

@Composable
fun StartMessageEditor(
    value: String,
    onValueChange: (String) -> Unit
) {
    var isFocused by remember {
        mutableStateOf(false)
    }
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxSize()
                .background(
                    color = Color(0xFFF8FBFF),
                    shape = RoundedCornerShape(20.dp)
                )
                .border(
                    width = if (isFocused) 2.dp else 1.dp,
                    color = if (isFocused) {
                        Color(0xFFDCEAFF)
                    } else {
                        Color(0xFFDCEAFF)
                    },
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(12.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .align(Alignment.BottomEnd),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "متن هوشمند",
                    tint = Color.DarkGray,
                    modifier = Modifier
                        .fillMaxSize(0.058f)
                        .aspectRatio(1f)
                        .clickable {
                            // متن هوشمند
                        }
                )

                Spacer(
                    modifier = Modifier.width(22.dp)
                )

                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "نام کاربر",
                    tint = Color.DarkGray,
                    modifier = Modifier
                        .fillMaxSize(0.075f)
                        .aspectRatio(1f)
                        .clickable {
                            // درج نام کاربر
                        }
                )

                Spacer(
                    modifier = Modifier.width(22.dp)
                )

                Text(
                    text = "Aa",
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.clickable {

                    }
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
            ) {

                BasicTextField(
                    value = value,
                    onValueChange = { newValue ->
                        onValueChange(newValue.take(300))
                    },

                    maxLines = 4,
                    singleLine = false,
                    modifier = Modifier
                        .verticalScroll(scrollState)
                        .fillMaxWidth()
                        .padding(
                            top = if (value.isEmpty()) 42.dp else 0.dp,
                            bottom = if (value.isEmpty()) 20.dp else 18.dp,
                            ),
                    textStyle = TextStyle(
                        fontFamily = Yekan,
                        fontSize = 12.sp,
                        color = Color(0xFF0F172A),
                        textAlign = Right
                    ),
                )


                if (value.isEmpty()) {
                    Text(
                        text = "متن پیام شروع",
                        fontFamily = Yekan,
                        fontSize = 12.sp,
                        color = Color(0xFF64748B),
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(
                                end = 8.dp
                            )
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(
                                horizontal = 8.dp,
                                vertical = 5.dp
                            )
                    )
                }
            }
        }
    }
}
