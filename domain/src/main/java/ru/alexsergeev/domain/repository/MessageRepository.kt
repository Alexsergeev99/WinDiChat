package ru.alexsergeev.domain.repository

import androidx.core.app.NotificationCompat.MessagingStyle.Message
import kotlinx.coroutines.flow.Flow
import ru.alexsergeev.domain.models.MessageDomainModel

interface MessageRepository {
    fun getAllMessages(): Flow<List<MessageDomainModel>>
    suspend fun sendMessage(message: MessageDomainModel)
}