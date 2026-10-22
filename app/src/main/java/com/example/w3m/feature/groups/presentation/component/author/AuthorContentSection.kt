package com.example.w3m.feature.groups.presentation.component.author



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.R
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

@Composable
fun AuthorContentSection(
    textSelected: Boolean,
    imageSelected: Boolean,
    onTextChange: (Boolean) -> Unit,
    onImageChange: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "نوع محتوای انتشار",
            fontFamily = Yekan,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "حداقل یک نوع محتوا باید انتخاب شود.",
            fontFamily = Yekan,
            fontSize = 12.sp
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AuthorContentTypeCard(
                title = "متن",
                price = "۶٬۰۰۰ تومان",
                selected = textSelected,
                icon = R.drawable.chat,
                modifier = Modifier.weight(1f),
                onClick = {
                    if (!textSelected || imageSelected) {
                        onTextChange(!textSelected)
                    }
                }
            )

            AuthorContentTypeCard(
                title = "تصویر",
                price = "8٬۰۰۰ تومان",
                selected = imageSelected,
                icon = R.drawable.image,
                modifier = Modifier.weight(1f),
                onClick = {
                    if (!imageSelected || textSelected) {
                        onImageChange(!imageSelected)
                    }
                }
            )
        }
    }
}
