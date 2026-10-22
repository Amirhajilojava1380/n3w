package com.example.w3m.feature.groups.presentation.screen.botGroups

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.feature.groups.domain.ChatType
import com.example.w3m.feature.groups.domain.GroupAction
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupActionBottomSheet(
    action: GroupAction,
    chatType: ChatType,
    onDismiss: () -> Unit,
    onSave: () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        containerColor = Color.White,
        shape = RoundedCornerShape(
            topStart = 22.dp,
            topEnd = 22.dp
        ),
        dragHandle = null,
        modifier = Modifier.heightIn(max = 700.dp),
        sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = true,
            confirmValueChange = { newValue ->
                if (newValue == SheetValue.Hidden) {
                    false
                } else {
                    true
                }
            }
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                when (action) {
                    GroupAction.BOT -> {
                        BotAiSettingsPage()
                    }

                    GroupAction.RULES -> {
                        BotWorkingSettings()
                    }

                    GroupAction.AUTHOR -> {
                        AuthorSettingsPage()
                    }

                    GroupAction.VIEWS -> {

                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(
                        horizontal = 18.dp,
                        vertical = 14.dp
                    ),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                OutlinedButton(
                    onClick = {
                        onDismiss()
                    },
                    modifier = Modifier
                        .weight(0.75f)
                        .height(50.dp),
                    shape = RoundedCornerShape(14.dp),
                    border = BorderStroke(
                        1.dp,
                        Color(0xFF2563EB)
                    )
                ) {
                    Text(
                        text = "انصراف",
                        fontFamily = Yekan,
                        fontSize = 14.sp,
                        color = Color(0xFF2563EB)
                    )
                }


                Button(
                    onClick = onSave,
                    modifier = Modifier
                        .weight(1.8f)
                        .height(50.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2563EB)
                    )
                ) {

                    Text(
                        text = "ذخیره تنظیمات",
                        fontFamily = Yekan,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}