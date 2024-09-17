package ru.alexsergeev.domain.usecases.interfaces

import kotlinx.coroutines.flow.Flow
import ru.alexsergeev.domain.models.MessageDomainModel

interface GetAllMessagesUseCase {
    fun invoke(): Flow<List<MessageDomainModel>>
}