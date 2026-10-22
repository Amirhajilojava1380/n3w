package com.example.w3m.feature.auth.presentation.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.LayoutDirection
import com.example.w3m.feature.auth.presentation.screen.ui.theme.W3mTheme
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

@Composable
fun PhoneTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

    val interactionSource = remember { MutableInteractionSource() }
    val focused by interactionSource.collectIsFocusedAsState()

    OutlinedTextField(
        value = value,

        onValueChange = { newValue ->
            if (
                newValue.text.length <= 10 &&
                newValue.text.all(Char::isDigit)
            ) {
                onValueChange(newValue)
            }
        },

        modifier = modifier
            .fillMaxWidth()
            .height(14.dp)
            .animateContentSize(),

        interactionSource = interactionSource,
        singleLine = true,
        textStyle = TextStyle(
            fontFamily = Yekan,
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Left,
            lineHeight = 24.sp
        ),


        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),

        placeholder = {
            Text(
                "9120000000",
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                fontFamily = Yekan,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
            )
        },

        leadingIcon = {

            Icon(
                modifier = modifier.size(20.dp),
                imageVector = Icons.Outlined.Phone,
                contentDescription = null,
                tint = if (focused)
                    Color(0xFF3B82F6)
                else
                    Color.Gray
            )


        },

        trailingIcon = {

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {

                Text(
                    text = "+98",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3B82F6),
                    textAlign = TextAlign.End,
                )

                Spacer(Modifier.width(10.dp))

                HorizontalDivider(
                    modifier = Modifier
                        .height(22.dp)
                        .width(1.dp),
                    thickness = DividerDefaults.Thickness, color = Color(0xFFE5E7EB)
                )

            }

        },

        colors = OutlinedTextFieldDefaults.colors(

            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,

            focusedBorderColor = Color(0xFF3B82F6),
            unfocusedBorderColor = Color(0xFFE5E7EB),

            cursorColor = Color(0xFF3B82F6)

        ),

        shape = RoundedCornerShape(18.dp)
    )
}

}

@Preview(showBackground = true, device = Devices.PIXEL_7_PRO, showSystemUi = true)
@Composable
fun GreetingPreview() {
    W3mTheme {
        var phone by rememberSaveable(
            stateSaver = TextFieldValue.Saver
        ) {
            mutableStateOf(TextFieldValue(""))
        }
        PhoneTextField(
            value = phone,
            onValueChange = {
                phone = it
            }
        )
    }
}
