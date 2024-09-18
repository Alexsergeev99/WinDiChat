package ru.alexsergeev.domain.usecases.interfaces

import ru.alexsergeev.domain.models.MessageDomainModel

interface SendMessageUseCase {
    suspend fun invoke(message: MessageDomainModel)
}