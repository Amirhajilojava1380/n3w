package com.example.w3m.feature.auth.presentation.component

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

@Composable
fun WebsiteText() {

    val context = LocalContext.current


    Text(
        fontFamily = Yekan,
        fontSize = 14.sp,
        text = "قوانین و شرایط استفاده",
        color = Color(0xFF2563EB),
        modifier = Modifier.clickable {

            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://w3m.ir")
            )
            context.startActivity(intent)
        },
        textAlign = TextAlign.Center
    )
}
