package com.example.w3m.feature.bots.presentation.component.botsetting.startmessage


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.feature.intro.presentation.screen.ui.theme.ColorBul
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

data class BotButtonUiModel(
    val id: String,
    val title: String,
    val isEnabled: Boolean
)

@Composable
fun ButtonsManagementPage(
    buttons: List<BotButtonUiModel>,
    onAddClick: () -> Unit,
    onToggle: (BotButtonUiModel, Boolean) -> Unit
) {
    var buttonList by remember {
        mutableStateOf(buttons)
    }
    Spacer(
        modifier = Modifier.size(18.dp)
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "مدیریت دکمه‌ها",
                    fontFamily = Yekan,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A)
                )

            }

            AddButton(
                onClick = onAddClick
            )
        }

        Spacer(
            modifier = Modifier.size(18.dp)
        )

        // Buttons
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            buttons.forEach { button ->

                BotButtonItem(
                    button = button,
                    onToggle = { enabled ->

                        buttonList = buttonList.map {
                            if (it.id == button.id) {
                                it.copy(isEnabled = enabled)
                            } else {
                                it
                            }
                        }

                        onToggle(button, enabled)
                    }
                )
            }
        }
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
                horizontal = 10.dp,
                vertical = 12.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = Icons.Outlined.Add,
            contentDescription = "افزودن دکمه",
            modifier = Modifier.fillMaxSize(0.1f),
            tint = Color.White
        )

        Spacer(
            modifier = Modifier.width(8.dp)
        )

        Text(
            text = "افزودن دکمه",
            fontFamily = Yekan,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
private fun BotButtonItem(
    button: BotButtonUiModel,
    onToggle: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFF8FBFF),
                shape = RoundedCornerShape(20.dp)
            )
            .border(
                width = 1.dp,
                color = Color(0xFFDCEAFF),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(
                horizontal = 18.dp,
                vertical = 20.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            horizontalAlignment = Alignment.End
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = button.title,
                    fontFamily = Yekan,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A)
                )
                Spacer(
                    modifier = Modifier.weight(1f)
                )

                Switch(
                    checked = button.isEnabled,
                    onCheckedChange = onToggle,
                    modifier = Modifier.scale(0.75f),
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.White,
                        checkedTrackColor = ColorBul,
                        uncheckedThumbColor = Color.White,
                        uncheckedTrackColor = Color(0xFFB8C0CC),
                        uncheckedBorderColor = Color.Transparent,
                        checkedBorderColor = Color.Transparent
                    )
                )
            }
        }

    }
}
