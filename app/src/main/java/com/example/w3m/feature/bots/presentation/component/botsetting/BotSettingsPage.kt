package com.example.w3m.feature.bots.presentation.component.botsetting

sealed interface BotSettingsPage {

    data object Main : BotSettingsPage
    data object StartMessage : BotSettingsPage
    data object ManagementPage : BotSettingsPage
    data object Ai : BotSettingsPage
    data object Backup : BotSettingsPage
}