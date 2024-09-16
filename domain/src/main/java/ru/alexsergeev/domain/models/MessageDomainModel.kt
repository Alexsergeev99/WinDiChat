package ru.alexsergeev.domain.models

data class MessageDomainModel(
    val id: Long,
    val senderId: Int,
    val recipientId: Int,
    val text: String,
    val date: String
)