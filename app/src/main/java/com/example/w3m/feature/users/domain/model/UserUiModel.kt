package com.example.w3m.feature.users.domain.model

data class UserUiModel(
    val id: Long,
    val name: String,
    val phone: String,
    val telegramUsername: String,
    val avatarUrl: String? = null
)