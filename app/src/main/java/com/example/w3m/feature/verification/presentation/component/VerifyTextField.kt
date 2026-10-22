package com.example.w3m.feature.verification.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

@Composable
fun VerifyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "",
    placeholder: String = "",
    leadingIcon: ImageVector? = null,
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp),

        singleLine = true,
        textStyle = TextStyle(
            fontFamily = Yekan,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Right,
        ),
        shape = RoundedCornerShape(18.dp),

        label = {
            Text(
                text = label,
                fontFamily = Yekan,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
            )
        },

        placeholder = {
            Text(
                text = placeholder,
                fontFamily = Yekan,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
            )
        },

        leadingIcon = {
            leadingIcon?.let {
                Icon(
                    imageVector = it,
                    contentDescription = null
                )
            }
        },

        colors = OutlinedTextFieldDefaults.colors(

            focusedBorderColor = Color(0xFF2563EB),
            unfocusedBorderColor = Color(0xFFE5E7EB),

            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,

            cursorColor = Color(0xFF2563EB)
        )
    )
}