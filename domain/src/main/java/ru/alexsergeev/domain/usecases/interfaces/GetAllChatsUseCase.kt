package ru.alexsergeev.domain.usecases.interfaces

import kotlinx.coroutines.flow.Flow
import ru.alexsergeev.domain.models.ChatDomainModel
import ru.alexsergeev.domain.models.MessageDomainModel

interface GetAllChatsUseCase {
    fun invoke(): Flow<List<ChatDomainModel>>
}

interface SendMessageUseCase {
    suspend fun invoke(message: MessageDomainModel)
}