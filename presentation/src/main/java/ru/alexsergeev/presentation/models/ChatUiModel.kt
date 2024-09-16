package ru.alexsergeev.presentation.models

data class ChatUiModel(
    val id: Long,
    val firstUserId: Int,
    val secondUserId: Int,
    val messagesId: List<Int>,
)