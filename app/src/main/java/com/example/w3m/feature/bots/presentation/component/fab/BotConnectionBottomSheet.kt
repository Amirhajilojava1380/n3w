package com.example.w3m.feature.bots.presentation.component.fab
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.R
import com.example.w3m.feature.intro.presentation.screen.ui.theme.ColorBul
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.example.w3m.feature.bots.presentation.component.botsetting.startmessage.BotPlatform

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BotConnectionBottomSheet(
    onDismiss: () -> Unit
) {

    var selectedPlatform by remember {
        mutableStateOf(BotPlatform.TELEGRAM)
    }
    var token by remember {
        mutableStateOf("")
    }

    var platformMenuExpanded by remember {
        mutableStateOf(false)
    }
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true
        ),
        containerColor = Color.White,
        shape = RoundedCornerShape(
            topStart = 22.dp,
            topEnd = 22.dp
        ),
        dragHandle = null
    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 22.dp,
                    vertical = 20.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "اتصال پلتفرم جدید",
                fontFamily = Yekan,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0F172A)
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = "ربات خود را به پلتفرم مورد نظر متصل کنید",
                fontFamily = Yekan,
                fontSize = 14.sp,
                color = Color(0xFF64748B)
            )
        }

        // Main Content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .background(
                    color = Color(0xFFFCFCFD),
                    shape = RoundedCornerShape(14.dp)
                )
                .border(
                    width = 1.dp,
                    color = Color(0xFFF1F5F9),
                    shape = RoundedCornerShape(14.dp)
                )
                .padding(18.dp),
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = "پلتفرم",
                fontFamily = Yekan,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0F172A)
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.13f)
                    .background(
                        color = Color(0xFFF8FAFC),
                        shape = RoundedCornerShape(18.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = Color(0xFF93C5FD),
                        shape = RoundedCornerShape(18.dp)
                    )
                    .padding(
                        horizontal = 18.dp,
                        vertical = 14.dp
                    )
                    .clickable {
                        platformMenuExpanded = true
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Outlined.ArrowDropDown,
                    contentDescription = "انتخاب پلتفرم",
                    modifier = Modifier.fillMaxWidth(0.1f),
                    tint = Color(0xFF03A9F4)
                )

                Text(
                    text = when (selectedPlatform) {
                        BotPlatform.TELEGRAM -> "تلگرام"
                        BotPlatform.BALE -> "بله"
                        BotPlatform.WEB -> "وب سایت"
                    },
                    modifier = Modifier.fillMaxWidth(0.3f),
                    fontFamily = Yekan,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A)
                )

                Spacer(
                    modifier = Modifier.weight(1f)
                )



                Box(
                    modifier = Modifier
                        .fillMaxSize(0.23f)
                        .aspectRatio(1f)
                        .background(
                            color = Color(0xFFE0F2FE),
                            shape = RoundedCornerShape(14.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = when (selectedPlatform) {
                            BotPlatform.TELEGRAM -> painterResource(R.drawable.telegram)
                            BotPlatform.BALE -> painterResource(R.drawable.bale)
                            BotPlatform.WEB -> painterResource(R.drawable.web)
                        },
                        contentDescription = "انتخاب پلتفرم",
                        modifier = Modifier.fillMaxSize(0.7f),
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
                        expanded = platformMenuExpanded,
                        onDismissRequest = {
                            platformMenuExpanded = false
                        },
                        modifier = Modifier
                            .background(Color.White)
                            .fillMaxWidth(0.7f),
                    ) {
                        if (selectedPlatform != BotPlatform.TELEGRAM) {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = "تلگرام",
                                        fontFamily = Yekan,
                                        fontSize = 14.sp
                                    )
                                },
                                onClick = {
                                    selectedPlatform = BotPlatform.TELEGRAM
                                    platformMenuExpanded = false
                                }
                            )
                        }

                        if (selectedPlatform != BotPlatform.BALE) {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = "بله",
                                        fontFamily = Yekan,
                                        fontSize = 14.sp
                                    )
                                },
                                onClick = {
                                    selectedPlatform = BotPlatform.BALE
                                    platformMenuExpanded = false
                                }
                            )
                        }


                        if (selectedPlatform != BotPlatform.WEB) {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = "وب",
                                        fontFamily = Yekan,
                                        fontSize = 14.sp
                                    )
                                },
                                onClick = {
                                    selectedPlatform = BotPlatform.WEB
                                    platformMenuExpanded = false
                                }
                            )
                        }
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            Text(
                text = when (selectedPlatform) {
                    BotPlatform.WEB -> "ادرس وب سایت"
                    BotPlatform.TELEGRAM -> "توکن ربات"
                    BotPlatform.BALE -> "توکن ربات"
                },
                fontFamily = Yekan,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0F172A)
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            // Token Field
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.16f)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFE0F2FE),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(horizontal = 18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize(0.13f)
                        .aspectRatio(1f)
                        .background(
                            color = Color(0xFFE0F2FE),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = "توکن",
                        modifier = Modifier.fillMaxSize(0.8f),
                        tint = ColorBul
                    )
                }

                Spacer(
                    modifier = Modifier.width(14.dp)
                )

                OutlinedTextField(
                    value = token,
                    onValueChange = {
                        token = it
                    },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = if (selectedPlatform == BotPlatform.WEB) {
                                "https://example.com"
                            } else {
                                "توکن دریافتی از بات فادر"
                            },
                            modifier = Modifier.fillMaxWidth(),
                            fontFamily = Yekan,
                            fontSize = 14.sp,
                            color = Color(0xFF94A3B8),
                            textAlign = if (selectedPlatform == BotPlatform.WEB) {
                                TextAlign.End
                            } else {
                                TextAlign.Start
                            }
                        )
                    },
                    textStyle = TextStyle(
                        fontFamily = Yekan,
                        fontSize = 14.sp,
                        textAlign = TextAlign.End
                    ),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent,
                        disabledBorderColor = Color.Transparent,
                        errorBorderColor = Color.Transparent
                    )
                )
            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            // Info
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFFEFF6FF),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(
                        horizontal = 8.dp,
                        vertical = 20.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "راهنما",
                    modifier = Modifier.fillMaxSize(0.1f),
                    tint = Color(0xFF2563EB)
                )

                Spacer(
                    modifier = Modifier.size(8.dp)
                )

                Text(
                    text = when (selectedPlatform) {
                        BotPlatform.TELEGRAM -> "توکن ربات را از botfather@ در تلگرام دریافت کنید."
                        BotPlatform.WEB -> "آدرس وب سایت خود را وارد کنید. پس از اتصال، اسکریپت اختصاصی دریافت خواهید کرد."
                        BotPlatform.BALE -> "توکن ربات را از botfather@ در بله دریافت کنید."
                    },
                    modifier = Modifier.weight(1f),
                    fontFamily = Yekan,
                    fontSize = 14.sp,
                    color = Color(0xFF475569)
                )
            }
        }


        Spacer(
            modifier = Modifier.height(3.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(
                    horizontal = 18.dp,
                    vertical = 14.dp
                ),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            OutlinedButton(
                onClick = {
                    onDismiss()
                },
                modifier = Modifier
                    .weight(0.75f)
                    .height(50.dp),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(
                    1.dp,
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
                onClick = {},
                modifier = Modifier
                    .weight(1.8f)
                    .height(50.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2563EB)
                )
            ) {

                Text(
                    text = "اتصال پلتفرم جدید",
                    fontFamily = Yekan,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
