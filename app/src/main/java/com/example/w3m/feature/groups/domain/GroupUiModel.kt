package com.example.w3m.feature.groups.domain


data class GroupUiModel(
    val id: Int,
    val name: String,
    val username: String,
    val chatType: ChatType,
    val platform: MessagingPlatform,
    val count: Int
)