package ru.alexsergeev.domain.usecases.implementation

import ru.alexsergeev.domain.models.MessageDomainModel
import ru.alexsergeev.domain.repository.MessageRepository
import ru.alexsergeev.domain.usecases.interfaces.SendMessageUseCase

internal class SendMessageUseCaseImpl(
    private val repository: MessageRepository
) : SendMessageUseCase {
    override suspend fun invoke(message: MessageDomainModel) = repository.sendMessage(message)
}