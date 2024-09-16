package ru.alexsergeev.domain.usecases.implementation

import kotlinx.coroutines.flow.Flow
import ru.alexsergeev.domain.models.ChatDomainModel
import ru.alexsergeev.domain.repository.ChatsRepository
import ru.alexsergeev.domain.usecases.interfaces.GetAllChatsUseCase

internal class GetAllChatsUseCaseImpl(
    private val repository: ChatsRepository
) : GetAllChatsUseCase {
    override fun invoke(): Flow<List<ChatDomainModel>> = repository.getAllChats()
}