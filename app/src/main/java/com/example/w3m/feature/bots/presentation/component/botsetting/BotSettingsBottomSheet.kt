package com.example.w3m.feature.bots.presentation.component.botsetting

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.feature.bots.domain.model.BotUiModel
import com.example.w3m.feature.bots.presentation.component.botsetting.startmessage.BotButtonUiModel
import com.example.w3m.feature.bots.presentation.screen.botsetting.AiSettingsPage
import com.example.w3m.feature.bots.presentation.screen.botsetting.BackupSettingsPage
import com.example.w3m.feature.bots.presentation.screen.botsetting.BotManagementPage
import com.example.w3m.feature.bots.presentation.screen.botsetting.BotSettingsMainPage
import com.example.w3m.feature.bots.presentation.screen.botsetting.BotStartMessagePage
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BotSettingsBottomSheet(
    list: List<BotButtonUiModel>,
    bot: BotUiModel,
    onDismiss: () -> Unit,
    onSave: () -> Unit,
    onStartMessageClick: () -> Unit = {},
    onButtonsClick: () -> Unit = {},
    onSmartReplyClick: () -> Unit = {},
    onBackupClick: () -> Unit = {}
) {

    var currentPage by remember {
        mutableStateOf<BotSettingsPage>(
            BotSettingsPage.Main
        )
    }
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
        AnimatedContent(
            targetState = currentPage,

            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            transitionSpec = {

                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left
                ) + EnterTransition.None togetherWith
                        slideOutOfContainer(
                            towards = AnimatedContentTransitionScope.SlideDirection.Left
                        ) + ExitTransition.None
            },
            label = "bot_settings_content"
        ) { page ->

            when (page) {

                BotSettingsPage.Main -> {
                    BotSettingsMainPage(
                        onStartMessageClick = {
                            currentPage = BotSettingsPage.StartMessage
                            onStartMessageClick()
                        },
                        onButtonsClick = {
                            currentPage = BotSettingsPage.ManagementPage
                            onButtonsClick()
                        },
                        onSmartReplyClick = {
                            currentPage = BotSettingsPage.Ai
                            onSmartReplyClick()
                        },
                        onBackupClick = {
                            currentPage = BotSettingsPage.Backup
                            onBackupClick()
                        }
                    )
                }

                BotSettingsPage.StartMessage -> {
                    BotStartMessagePage(
                        platformType = bot.botPlatform
                    )
                }

                BotSettingsPage.ManagementPage -> {
                    BotManagementPage(
                        buttons = list,
                        onAddClick = {
                        },
                        onEditClick = { button ->
                        },
                        onDeleteClick = { button ->
                        },
                        onLinkClick = { button ->
                        },
                        onItemClick = { button ->
                        }
                    )
                }

                BotSettingsPage.Ai -> {
                    AiSettingsPage()
                }
                BotSettingsPage.Backup -> {
                    BackupSettingsPage()
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
                    if (currentPage != BotSettingsPage.Main) {
                        currentPage = BotSettingsPage.Main
                    } else {
                        onDismiss()
                    }
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
                    text = if (
                        currentPage != BotSettingsPage.Main
                    ) {
                        "بازگشت"
                    } else {
                        "انصراف"
                    },
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
