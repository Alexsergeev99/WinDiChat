package ru.alexsergeev.domain.usecases.implementation

import kotlinx.coroutines.flow.Flow
import ru.alexsergeev.domain.models.ChatDomainModel
import ru.alexsergeev.domain.models.MessageDomainModel
import ru.alexsergeev.domain.repository.ChatsRepository
import ru.alexsergeev.domain.repository.MessageRepository
import ru.alexsergeev.domain.usecases.interfaces.GetAllChatsUseCase
import ru.alexsergeev.domain.usecases.interfaces.GetAllMessagesUseCase

internal class GetAllChatsUseCaseImpl(
    private val repository: ChatsRepository
) : GetAllChatsUseCase {
    override fun invoke(): Flow<List<ChatDomainModel>> = repository.getAllChats()
}

internal class GetAllMessagesUseCaseImpl(
    private val repository: MessageRepository
) : GetAllMessagesUseCase {
    override fun invoke(): Flow<List<MessageDomainModel>> = repository.getAllMessages()
}