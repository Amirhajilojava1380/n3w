package com.example.w3m.feature.bots.presentation.component.botsetting.startmessage
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Info
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.R
import com.example.w3m.feature.intro.presentation.screen.ui.theme.ColorBul
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

@Composable
fun StartMessageFilePicker(
    onFileSelected: (Uri?) -> Unit
) {

    var isMediaEnabled by remember {
        mutableStateOf(false)
    }

    var selectedFile by remember {
        mutableStateOf<Uri?>(null)
    }

    val filePickerLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri ->

            selectedFile = uri
            onFileSelected(uri)
        }
    Spacer(
        modifier = Modifier.height(20.dp)
    )
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFFF8FBFF),
                    shape = RoundedCornerShape(20.dp)
                )
                .border(
                    width = 1.dp,
                    color = Color(0xFFDCEAFF),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(
                    horizontal = 18.dp,
                    vertical = 18.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {


            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(0.13f)
                        .aspectRatio(1f)
                        .background(
                            color = Color(0xFFEAF2FF),
                            shape = RoundedCornerShape(50.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = "اطلاعات رسانه",
                        modifier = Modifier.fillMaxSize(0.6f),
                        tint = ColorBul
                    )
                }
                Spacer(
                    modifier = Modifier.width(16.dp)
                )
                Column(
                    horizontalAlignment = Alignment.Start
                ) {

                    Text(
                        text = "رسانه همراه پیام",
                        fontFamily = Yekan,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0F172A),
                        textAlign = TextAlign.Left
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = "امکان ارسال فایل به همراه پیام",
                        fontFamily = Yekan,
                        fontSize = 12.sp,
                        color = Color(0xFF7A879C),
                        textAlign = TextAlign.Left
                    )

                }
                Spacer(
                    modifier = Modifier.weight(1f)
                )
                Switch(
                    checked = isMediaEnabled,
                    onCheckedChange = { enabled ->

                        isMediaEnabled = enabled

                        if (!enabled) {
                            selectedFile = null
                            onFileSelected(null)
                        }
                    },
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

        AnimatedVisibility(
            visible = isMediaEnabled
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 12.dp
                    )
            ) {

                // ─────────────────────
                // انتخاب فایل
                // ─────────────────────

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                        .background(
                            color = Color(0xFFF5F9FF),
                            shape = RoundedCornerShape(18.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = Color(0xFFDCEAFF),
                            shape = RoundedCornerShape(18.dp)
                        )
                        .clickable {
                            filePickerLauncher.launch("*/*")
                        },
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Icon(
                            painter = painterResource(R.drawable.folder),
                            contentDescription = "انتخاب فایل",
                            modifier = Modifier.fillMaxSize(0.2f),
                            tint = ColorBul
                        )

                        Spacer(
                            modifier = Modifier.height(6.dp)
                        )

                        Text(
                            text = "انتخاب فایل از حافظه دستگاه",
                            fontFamily = Yekan,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF38547D),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "هر نوع فایل را می‌توانید انتخاب کنید",
                            fontFamily = Yekan,
                            fontSize = 12.sp,
                            color = Color(0xFF7B8CA6),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                AnimatedVisibility(
                    visible = selectedFile != null
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp)
                            .background(
                                color = Color(0xFFF5F9FF),
                                shape = RoundedCornerShape(18.dp)
                            )
                            .padding(
                                horizontal = 16.dp,
                                vertical = 14.dp
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .size(18.dp)
                                .background(
                                    color = Color(0xFFEAF1FF),
                                    shape = RoundedCornerShape(14.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                painter = painterResource(R.drawable.folder),
                                contentDescription = "فایل",
                                modifier = Modifier.fillMaxSize(0.4f),
                                tint = Color(0xFF536CF2)
                            )
                        }

                        Spacer(
                            modifier = Modifier.width(14.dp)
                        )

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {

                            Text(
                                text = selectedFile
                                    ?.lastPathSegment
                                    ?: "فایل انتخاب شده",
                                fontFamily = Yekan,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF0F172A),
                                maxLines = 1
                            )

                            Spacer(
                                modifier = Modifier.height(4.dp)
                            )

                            Text(
                                text = "فایل انتخاب شده",
                                fontFamily = Yekan,
                                fontSize = 12.sp,
                                color = Color(0xFF7A879C)
                            )
                        }

                        Spacer(
                            modifier = Modifier.width(10.dp)
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxSize(0.2f)
                                .aspectRatio(1f)
                                .background(
                                    color = Color(0xFFE9EEF6),
                                    shape = RoundedCornerShape(50.dp)
                                )
                                .clickable {

                                    selectedFile = null
                                    onFileSelected(null)
                                },
                            contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                imageVector = Icons.Outlined.Close,
                                contentDescription = "حذف فایل",
                                modifier = Modifier.fillMaxSize(0.6f),
                                tint = Color(0xFF65758D)
                            )
                        }
                    }
                }
            }
        }
    }
}
