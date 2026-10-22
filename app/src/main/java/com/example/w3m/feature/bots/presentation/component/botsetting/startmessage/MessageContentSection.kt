package com.example.w3m.feature.bots.presentation.component.botsetting.startmessage


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.feature.intro.presentation.screen.ui.theme.ColorBul
import com.example.w3m.feature.intro.presentation.screen.ui.theme.ColorBulLight
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

@Composable
fun MessageContentSection(
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {


    OutlinedTextField(
        value = title,
        onValueChange = onTitleChange,
        modifier = modifier
            .fillMaxWidth(),

        singleLine = true,
        textStyle = TextStyle(
            fontFamily = Yekan,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Right,
        ),
        shape = RoundedCornerShape(18.dp),

        label = {
            Text(
                text = "عنوان پیام",
                fontFamily = Yekan,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
            )
        },

        placeholder = {
            Text(
                text = "اسم سایت",
                fontFamily = Yekan,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.LightGray
            )
        },

        leadingIcon = {
            MessageIconBox(
                contentDescription = "ویرایش"
            )
        },


        colors = OutlinedTextFieldDefaults.colors(

            focusedBorderColor = Color(0xFF2563EB),
            unfocusedBorderColor = Color(0xFFDCEAFF),

            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,

            cursorColor = Color(0xFF2563EB)
        )
    )



    Spacer(
        modifier = Modifier.height(20.dp)
    )


    OutlinedTextField(
        value = description,
        modifier = modifier
            .fillMaxWidth()
            .height(130.dp),
        onValueChange = { newValue ->
            onDescriptionChange(newValue.take(300))
        },
        maxLines = 4,
        singleLine = false,
        textStyle = TextStyle(
            fontFamily = Yekan,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Right,
        ),
        shape = RoundedCornerShape(18.dp),

        label = {
            Text(
                text = "توضیحات کوتاه",
                fontFamily = Yekan,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
            )
        },

        placeholder = {
            Text(
                text = "اسم سایت",
                fontFamily = Yekan,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.LightGray
            )
        },

        leadingIcon = {
            MessageIconBox(
                contentDescription = "پیام"
            )
        },

        colors = OutlinedTextFieldDefaults.colors(

            focusedBorderColor = Color(0xFF2563EB),
            unfocusedBorderColor = Color(0xFFDCEAFF),

            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,

            cursorColor = Color(0xFF2563EB)
        )
    )
}


@Composable
fun MessageIconBox(
    contentDescription: String
) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxSize(0.1f)
            .aspectRatio(1f)
            .background(
                color = ColorBulLight,
                shape = RoundedCornerShape(
                    size = 12.dp
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = if (contentDescription == "ویرایش") {
                Icons.Outlined.Edit
            } else {
                Icons.Outlined.Email
            },
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(0.7f),
            tint = ColorBul
        )
    }
}
