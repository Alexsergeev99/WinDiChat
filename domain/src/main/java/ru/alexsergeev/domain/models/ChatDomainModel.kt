package ru.alexsergeev.domain.models

data class ChatDomainModel(
    val id: Long,
    val firstUserId: Int,
    val secondUserId: Int,
    val messagesId: List<Int>,
)