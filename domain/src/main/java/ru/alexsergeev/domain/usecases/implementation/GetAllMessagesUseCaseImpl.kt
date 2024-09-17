package ru.alexsergeev.domain.usecases.implementation

import kotlinx.coroutines.flow.Flow
import ru.alexsergeev.domain.models.MessageDomainModel
import ru.alexsergeev.domain.repository.MessageRepository
import ru.alexsergeev.domain.usecases.interfaces.GetAllMessagesUseCase

internal class GetAllMessagesUseCaseImpl(
    private val repository: MessageRepository
) : GetAllMessagesUseCase {
    override fun invoke(): Flow<List<MessageDomainModel>> = repository.getAllMessages()
}