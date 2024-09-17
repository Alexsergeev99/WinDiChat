package ru.alexsergeev.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.alexsergeev.domain.models.MessageDomainModel

interface MessageRepository {
    fun getAllMessages(): Flow<List<MessageDomainModel>>
}