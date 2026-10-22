package com.example.w3m.feature.groups.presentation.component.author



import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

@Composable
fun AuthorContentTypeCard(
    title: String,
    price: String,
    selected: Boolean,
    icon: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .border(
                0.5.dp,
                if (selected) Color(0xFF3B82F6) else Color(0xFFE0E8F2),
                RoundedCornerShape(14.dp)
            )
            .background(
                if (selected) Color(0xFFF0F6FF) else Color.White,
                RoundedCornerShape(14.dp)
            )
            .clickable(onClick = onClick)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Checkbox(
            checked = selected,
            onCheckedChange = { onClick() },
            modifier = Modifier.size(14.dp),
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xFF3B82F6)
            )
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = title,
                fontFamily = Yekan,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = price,
                fontFamily = Yekan,
                fontSize = 12.sp,
                color = Color(0xFF0F9F6E)
            )
        }

        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = Color(0xFF3D82F4),
            modifier = Modifier.fillMaxSize(0.2f)
        )
    }
}
