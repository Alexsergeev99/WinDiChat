package ru.alexsergeev.domain.usecases.interfaces

import kotlinx.coroutines.flow.Flow
import ru.alexsergeev.domain.models.ChatDomainModel

interface GetAllChatsUseCase {
    fun invoke(): Flow<List<ChatDomainModel>>
}