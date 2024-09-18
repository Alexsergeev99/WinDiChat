package ru.alexsergeev.domain.usecases.implementation

import kotlinx.coroutines.flow.Flow
import ru.alexsergeev.domain.models.ChatDomainModel
import ru.alexsergeev.domain.models.MessageDomainModel
import ru.alexsergeev.domain.repository.ChatsRepository
import ru.alexsergeev.domain.repository.MessageRepository
import ru.alexsergeev.domain.repository.UserProfileRepository
import ru.alexsergeev.domain.usecases.interfaces.GetAllChatsUseCase
import ru.alexsergeev.domain.usecases.interfaces.GetAllMessagesUseCase
import ru.alexsergeev.domain.usecases.interfaces.RegisterUserUseCase
import ru.alexsergeev.domain.usecases.interfaces.SendCodeUseCase
import ru.alexsergeev.domain.usecases.interfaces.SendMessageUseCase

internal class GetAllChatsUseCaseImpl(
    private val repository: ChatsRepository
) : GetAllChatsUseCase {
    override fun invoke(): Flow<List<ChatDomainModel>> = repository.getAllChats()
}