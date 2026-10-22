package com.example.w3m.feature.bots.domain.model

import com.example.w3m.feature.bots.presentation.component.botsetting.startmessage.BotPlatform

data class BotUiModel(
    val name: String,
    val username: String,
    val isConnected: Boolean,
    val id : Int,
    val botPlatform: BotPlatform
)