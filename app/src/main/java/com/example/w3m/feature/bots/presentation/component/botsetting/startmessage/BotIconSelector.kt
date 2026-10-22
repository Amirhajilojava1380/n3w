package com.example.w3m.feature.bots.presentation.component.botsetting.startmessage

import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage


import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.R
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BotIconSelector() {

    var selectedIconUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val filePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        selectedIconUri = uri
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFFF8FAFC),
                    shape = RoundedCornerShape(18.dp)
                )
                .border(
                    width = 1.dp,
                    color = Color(0xFFDCEAFF),
                    shape = RoundedCornerShape(18.dp)
                )
            ,
            contentAlignment = Alignment.Center
        ) {

            if (selectedIconUri == null) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(8.dp)
                ) {

                    Button(
                        onClick = {
                            filePicker.launch("image/*")
                        },
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFEFF6FF)
                        ),
                        modifier = Modifier.padding(8.dp)
                    ) {

                        Icon(
                            painter = painterResource(R.drawable.folder),
                            contentDescription = "انتخاب فایل ",
                            tint = Color(0xFF2563EB),
                            modifier = Modifier.fillMaxSize(0.12f)
                        )

                        Spacer(
                            modifier = Modifier.size(12.dp)
                        )

                        Text(
                            text = "انتخاب فایل آیکون",
                            fontFamily = Yekan,
                            fontSize = 14.sp,
                            color = Color(0xFF2563EB)
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(2.dp)
                    )

                    Text(
                        text = "یا فایل را اینجا بکشید و رها کنید",
                        fontFamily = Yekan,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )


                    Text(
                        text = "فرمت‌های مجاز: PNG, JPEG, SVG  (حداکثر 5 مگابایت)",
                        fontFamily = Yekan,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }

            } else {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Box(
                        modifier = Modifier.fillMaxSize(0.2f).padding(top = 10.dp)
                    ) {

                        GlideImage(
                            model = selectedIconUri,
                            contentDescription = "آیکون انتخاب شده",
                            modifier = Modifier
                                .fillMaxWidth()

                                .clip(
                                    RoundedCornerShape(18.dp)
                                ),
                            contentScale = ContentScale.Fit
                        )

                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .fillMaxSize(0.25f)
                                .clip(CircleShape)
                                .background(Color(0xFFDC2626))
                                .clickable {
                                    selectedIconUri = null
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Close,
                                contentDescription = "حذف آیکون",
                                modifier = Modifier.fillMaxWidth(),
                                tint = Color.White
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = "برای تغییر فایل، روی آیکون کلیک کنید",
                        fontFamily = Yekan,
                        fontSize = 12.sp,
                        color = Color(0xFF64748B)
                    )
                    Text(
                        text = "فرمت‌های مجاز: PNG, JPEG, SVG  (حداکثر 5 مگابایت)",
                        fontFamily = Yekan,
                        fontSize = 12.sp,
                        color = Color(0xFF94A3B8)
                    )
                }
            }
        }
    }
}
