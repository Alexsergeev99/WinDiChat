package ru.alexsergeev.domain.usecases.implementation

import kotlinx.coroutines.flow.Flow
import ru.alexsergeev.domain.models.UserDomainModel
import ru.alexsergeev.domain.repository.ChatsRepository
import ru.alexsergeev.domain.usecases.interfaces.GetUserByIdUseCase

internal class GetUserByIdUseCaseImpl(
    private val repository: ChatsRepository
): GetUserByIdUseCase {
    override fun invoke(id: Int): Flow<UserDomainModel> = repository.getUserById(id)
}