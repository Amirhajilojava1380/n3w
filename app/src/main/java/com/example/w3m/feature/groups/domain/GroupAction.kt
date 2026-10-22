package com.example.w3m.feature.groups.domain

enum class GroupAction {
    BOT,
    RULES,
    AUTHOR,
    VIEWS
}


data class GroupActionSheetState(
    val chatType: ChatType,
    val action: GroupAction
)