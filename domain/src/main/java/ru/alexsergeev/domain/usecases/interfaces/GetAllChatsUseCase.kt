package ru.alexsergeev.domain.usecases.interfaces

import kotlinx.coroutines.flow.Flow
import ru.alexsergeev.domain.models.ChatDomainModel
import ru.alexsergeev.domain.models.MessageDomainModel

interface GetAllChatsUseCase {
    fun invoke(): Flow<List<ChatDomainModel>>
}

interface SendCodeUseCase {
    fun invoke(phone: String): Flow<Boolean>
}