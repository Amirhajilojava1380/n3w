package com.example.w3m.feature.groups.presentation.screen.botGroups
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.w3m.feature.bots.presentation.screen.botsetting.AiInstructionCard
import com.example.w3m.feature.bots.presentation.screen.botsetting.AiResponseCard
import com.example.w3m.feature.intro.presentation.screen.ui.theme.Yekan

@Composable
fun BotAiSettingsPage(modifier: Modifier = Modifier) {

    var isAiEnabled by remember {
        mutableStateOf(false)
    }

    var instruction by remember {
        mutableStateOf(TextFieldValue(""))
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 12.dp
            ),

        ) {
        Spacer(modifier = Modifier.height(25.dp))

        // عنوان
        Text(
            text = "تنظیمات هوش مصنوعی",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontFamily = Yekan,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF111827)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "پاسخهای دقیق با هوش مصنوعی",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontFamily = Yekan,
            fontSize = 12.sp,
            color = Color(0xFF6B7280)
        )

        Spacer(modifier = Modifier.height(18.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 12.dp)
                .navigationBarsPadding()
        ) {
            AiResponseCard(
                enabled = isAiEnabled,
                onEnabledChange = {
                    isAiEnabled = it
                }
            )
            AnimatedVisibility(
                visible = isAiEnabled,
                enter = expandVertically(
                    expandFrom = Alignment.Top
                ) + fadeIn(),
                exit = shrinkVertically(
                    shrinkTowards = Alignment.Top
                ) + fadeOut()
            ) {
                Column {
                    Spacer(
                        modifier = Modifier.height(14.dp)
                    )

                    AiInstructionCard(
                        instruction = instruction,
                        onInstructionChange = {
                            if (it.text.length <= 500) {
                                instruction = it
                            }
                        }
                    )
                }
            }
        }
    }
}
