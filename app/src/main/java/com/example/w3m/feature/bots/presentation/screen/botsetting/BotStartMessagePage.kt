package com.example.w3m.feature.bots.presentation.screen.botsetting
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text


import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.feature.bots.presentation.component.botsetting.startmessage.BotButtonUiModel
import com.example.w3m.feature.bots.presentation.component.botsetting.startmessage.BotIconSelector
import com.example.w3m.feature.bots.presentation.component.botsetting.startmessage.BotPlatform
import com.example.w3m.feature.bots.presentation.component.botsetting.startmessage.ButtonsManagementPage
import com.example.w3m.feature.bots.presentation.component.botsetting.startmessage.MessageContentSection
import com.example.w3m.feature.bots.presentation.component.botsetting.startmessage.StartMessageEditor
import com.example.w3m.feature.bots.presentation.component.botsetting.startmessage.StartMessageFilePicker
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

@Composable
fun BotStartMessagePage(
    platformType:BotPlatform
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        var selectedFileUri by remember {
            mutableStateOf<Uri?>(null)
        }
        var buttons by remember {
            mutableStateOf(
                listOf(
                    BotButtonUiModel(
                        id = "1",
                        title = "تر",
                        isEnabled = true
                    ),
                    BotButtonUiModel(
                        id = "2",
                        title = "سلامه",
                        isEnabled = false
                    )
                )
            )
        }

        var selectedPlatform by remember {
            mutableStateOf(BotPlatform.BALE)
        }

        var title by remember {
            mutableStateOf("")
        }

        var description by remember {
            mutableStateOf("")
        }
        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "شروع پیام",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontFamily = Yekan,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF111827)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "پیامی که هنگام شروع گفتگو نمایش داده میشود",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontFamily = Yekan,
            fontSize = 12.sp,
            color = Color(0xFF6B7280)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
                .verticalScroll(rememberScrollState())
                .padding(
                    start = 18.dp,
                    end = 18.dp,
                    bottom = 12.dp
                ),
            horizontalAlignment = Alignment.Start
        ) {

            when (platformType) {

                BotPlatform.TELEGRAM -> {
                    var startMessage by remember {
                        mutableStateOf("")
                    }
                    StartMessageEditor(
                        value = startMessage,
                        onValueChange = { startMessage = it }
                    )
                    StartMessageFilePicker(
                        onFileSelected = { uri ->
                            selectedFileUri = uri
                        }
                    )
                    ButtonsManagementPage(
                        buttons = buttons,
                        onAddClick = {

                        },
                        onToggle = { button, enabled ->
                            buttons = buttons.map {
                                if (it.id == button.id) {
                                    it.copy(
                                        isEnabled = enabled
                                    )
                                } else {
                                    it
                                }
                            }
                        }
                    )
                }

                BotPlatform.WEB -> {
                    Spacer(modifier = Modifier.height(18.dp))

                    MessageContentSection(
                        title = title,
                        onTitleChange = {
                            title = it
                        },
                        description = description,
                        onDescriptionChange = {
                            description = it
                        }
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    BotIconSelector()


                    ButtonsManagementPage(
                        buttons = buttons,
                        onAddClick = {

                        },
                        onToggle = { button, enabled ->
                            buttons = buttons.map {
                                if (it.id == button.id) {
                                    it.copy(
                                        isEnabled = enabled
                                    )
                                } else {
                                    it
                                }
                            }
                        }
                    )

                }

                BotPlatform.BALE -> {

                }
            }


        }
    }

}
