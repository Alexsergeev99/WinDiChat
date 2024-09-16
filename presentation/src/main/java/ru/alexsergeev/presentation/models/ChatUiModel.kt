package ru.alexsergeev.presentation.models

data class ChatUiModel(
    val id: Long,
    val firstUserId: Int,
    val secondUserId: Int,
    val messagesId: List<MessageUiModel>,
)

data class MessageUiModel(
    val id: Long,
    val senderId: Int,
    val recipientId: Int,
    val text: String,
    val date: String
)