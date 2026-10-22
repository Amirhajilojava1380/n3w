package com.example.w3m.feature.verification.presentation.component
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

@Composable
fun VerifyBox(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester,
    onNext: () -> Unit,
    onPrevious: () -> Unit
) {

    var isFocused by remember { mutableStateOf(false) }

    val borderColor =
        if (isFocused)
            Color(0xFF2563EB)
        else
            Color(0xFFE5E7EB)


    OutlinedTextField(
        value = value,
        onValueChange = {

            if (it.length <= 1 && it.all(Char::isDigit)) {

                onValueChange(it)

                if (it.isNotEmpty())
                    onNext()
                else{
                    onPrevious()
                }
            }
        },

        modifier = modifier
            .size(18.dp)
            .focusRequester(focusRequester)
            .onFocusChanged {
                isFocused = it.isFocused
            }.onKeyEvent{
                    event ->

                if (
                    event.type == KeyEventType.KeyDown &&
                    event.key == Key.Backspace &&
                    value.isEmpty()
                ) {
                    onPrevious()
                    true
                } else {
                    false
                }
            },

        singleLine = true,

        textStyle = LocalTextStyle.current.copy(
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = Yekan
        ),

        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        placeholder = {"-"},
        shape = RoundedCornerShape(18.dp),

        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = borderColor,
            unfocusedBorderColor = borderColor,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        )
    )
}
