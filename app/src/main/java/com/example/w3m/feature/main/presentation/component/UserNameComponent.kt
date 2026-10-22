package com.example.w3m.feature.main.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

@Composable
fun UsernameCom(modifier: Modifier = Modifier) {
    Column {


        Text(
            textAlign = TextAlign.Center,
            text = "09376007032",
            color = Color(0xFF6E6C6C),
            fontWeight = FontWeight.Normal,
            fontFamily = Yekan,
            fontSize = 14.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UserNamePreview() {
    UsernameCom()
}
