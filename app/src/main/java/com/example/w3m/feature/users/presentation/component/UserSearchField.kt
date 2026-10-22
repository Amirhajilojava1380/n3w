package com.example.w3m.feature.users.presentation.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.R
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

@Composable
fun UserSearchField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp)
            .animateContentSize()
        ,
        singleLine = true,
        textStyle = TextStyle(
            fontFamily = Yekan,
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Right,
            lineHeight = 24.sp
        ),


        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),

        placeholder = {
            Text(
                "جستجو",
                color = Color(0xFF111827),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                fontFamily = Yekan,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
            )
        },

        leadingIcon = {

            IconButton(
                onClick = {},
                modifier = Modifier
                    .padding(start = 10.dp)
                    .fillMaxSize(0.1f)
                    .clip(RoundedCornerShape(22.dp))
                    .background(Color(0xFFDCEAFF))
                    .aspectRatio(1f)
            ) {
                Icon(
                    modifier = modifier.size(20.dp),
                    painter = painterResource(R.drawable.search),
                    contentDescription = null,
                    tint = Color(0xFF111827),

                    )
            }

        },
        colors = OutlinedTextFieldDefaults.colors(

            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,

            focusedBorderColor = Color(0xFF3B82F6),
            unfocusedBorderColor = Color(0xFFDCEAFF),

            cursorColor = Color(0xFF3B82F6)

        ),

        shape = RoundedCornerShape(18.dp)
    )

    Spacer(
        modifier = Modifier.height(12.dp)
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        UserFilterDropdown(
            title = "ربات",
            value = "همه ربات‌ها",
            icon = R.drawable.robots,
            options = listOf(
                "همه ربات‌ها"
            ),
            onSelected = {},
            modifier = Modifier.weight(1f)
        )

        UserFilterDropdown(
            title = "گروه‌بندی",
            value = "همه گروه‌ها",
            icon = R.drawable.group,
            options = listOf(
                "همه گروه‌ها"
            ),
            onSelected = {},
            modifier = Modifier.weight(1f)
        )
    }
    Spacer(
        modifier = Modifier.height(12.dp)
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {



        Button(
            onClick = {
                // اعمال فیلتر
            },
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1677F2)
            )
        ) {

            Icon(
                painter = painterResource(R.drawable.filter),
                contentDescription = null,
                modifier = Modifier.size(19.dp),
                tint = Color.White
            )

            Spacer(
                modifier = Modifier.width(6.dp)
            )

            Text(
                text = "اعمال فیلتر",
                fontFamily = Yekan,
                fontSize = 14.sp
            )
        }

        OutlinedButton(
            onClick = {
                // لغو فیلتر
            },
            modifier = Modifier.weight(1f),
            shape = RoundedCornerShape(14.dp),
            border = BorderStroke(1.dp , Color(0xFFDCEAFF))
        ) {

            Icon(
                painter = painterResource(R.drawable.refresh),
                contentDescription = null,
                modifier = Modifier.size(19.dp),
                tint =  Color(0xFF111827),

                )

            Spacer(
                modifier = Modifier.width(6.dp)
            )

            Text(
                text = "لغو فیلتر",
                fontFamily = Yekan,
                fontSize = 12.sp,
                color =  Color(0xFF111827),

                )
        }
    }
}
