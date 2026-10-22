package com.example.w3m.feature.bots.presentation.screen.botsetting


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Share
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
import com.example.w3m.R
import com.example.w3m.feature.bots.presentation.component.botsetting.startmessage.BotButtonUiModel
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan


@Composable
fun BotManagementPage(
    buttons: List<BotButtonUiModel>,
    onAddClick: () -> Unit,
    onEditClick: (BotButtonUiModel) -> Unit,
    onDeleteClick: (BotButtonUiModel) -> Unit,
    onLinkClick: (BotButtonUiModel) -> Unit,
    onItemClick: (BotButtonUiModel) -> Unit,
    modifier: Modifier = Modifier
) {


    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp)

    ) {

        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 18.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "مدیریت دکمه‌ها",
                fontFamily = Yekan,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0F172A)
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = "هماهنگ با تمام بخش‌ها",
                fontFamily = Yekan,
                fontSize = 12.sp,
                color = Color(0xFF64748B)
            )
        }

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "دکمه‌ها",
                fontFamily = Yekan,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0F172A)
            )
            AddButton(
                onClick = onAddClick
            )
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )


        LazyColumn(
            modifier = modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            items(
                items = buttons,
                key = { it.id }
            ) { button ->

                BotButtonItem(
                    button = button,
                    onClick = {
                        onItemClick(button)
                    },
                    onEditClick = {
                        onEditClick(button)
                    },
                    onDeleteClick = {
                        onDeleteClick(button)
                    },
                    onLinkClick = {
                        onLinkClick(button)
                    }
                )
            }
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFFEFF6FF),
                    shape = RoundedCornerShape(18.dp)
                )
                .padding(
                    horizontal = 14.dp,
                    vertical = 14.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "ⓘ",
                fontSize = 24.sp,
                color = Color(0xFF2563EB)
            )

            Spacer(
                modifier = Modifier.size(10.dp)
            )

            Text(
                text = "برای تغییر ترتیب دکمه‌ها می‌توانید از فلش بالا و پایین استفاده کنید یا با کشیدن و رها کردن آن‌ها را جابه‌جا کنید.",
                modifier = Modifier.weight(1f),
                fontFamily = Yekan,
                fontSize = 12.sp,
                lineHeight = 20.sp,
                color = Color(0xFF475569)
            )
        }

        Spacer(
            modifier = Modifier.height(18.dp)
        )
    }
}

@Composable
private fun AddButton(
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .background(
                color = Color(0xFF3B82F6),
                shape = RoundedCornerShape(14.dp)
            )
            .clickable(onClick = onClick)
            .padding(
                horizontal = 12.dp,
                vertical = 12.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = Icons.Outlined.Add,
            contentDescription = "افزودن دکمه",
            modifier = Modifier.size(22.dp),
            tint = Color.White
        )

        Spacer(
            modifier = Modifier.size(8.dp)
        )

        Text(
            text = "افزودن دکمه",
            fontFamily = Yekan,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
private fun BotButtonItem(
    button: BotButtonUiModel,
    onClick: () -> Unit,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onLinkClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(22.dp)
            )
            .border(width = 0.5.dp, color = Color(0xFFDCEAFF),shape = RoundedCornerShape(22.dp))
            .clickable(onClick = onClick)
            .padding(
                horizontal = 14.dp,
                vertical = 20.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(R.drawable.arrow),
            contentDescription = "جزئیات",
            modifier = Modifier
                .fillMaxSize(0.06f)
                .aspectRatio(1f)
                .clickable(onClick = onClick),
            tint = Color(0xFF475569)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(6.dp)
        ) {

            Text(
                text = button.title,
                fontFamily = Yekan,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                color = Color(0xFF0F172A)
            )

        }



        Spacer(
            modifier = Modifier.size(12.dp)
        )


        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            ActionIcon(
                icon = Icons.Outlined.Edit,
                contentDescription = "ویرایش",
                backgroundColor = Color.White,
                borderColor = Color(0xFFD1D5DB),
                iconColor = Color(0xFF374151),
                onClick = onEditClick
            )
            ActionIcon(
                icon = Icons.Outlined.Delete,
                contentDescription = "حذف",
                backgroundColor = Color(0xFFFFF1F2),
                borderColor = Color(0xFFFFE4E6),
                iconColor = Color(0xFFEF4444),
                onClick = onDeleteClick
            )

            ActionIcon(
                icon = Icons.Outlined.Share,
                contentDescription = "لینک",
                backgroundColor = Color.White,
                borderColor = Color(0xFFD1D5DB),
                iconColor = Color(0xFF374151),
                onClick = onLinkClick,
            )
        }
    }
}

@Composable
private fun ActionIcon(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    contentDescription: String,
    backgroundColor: Color,
    borderColor: Color,
    iconColor: Color,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .size(40.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(0.4f),
            tint = iconColor
        )
    }
}
