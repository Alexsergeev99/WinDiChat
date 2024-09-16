package ru.alexsergeev.presentation.models

data class MessageUiModel(
    val id: Long,
    val senderId: Int,
    val recipientId: Int,
    val text: String,
    val date: String
)