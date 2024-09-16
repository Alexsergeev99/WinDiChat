package ru.alexsergeev.domain.usecases.implementation

import kotlinx.coroutines.flow.Flow
import ru.alexsergeev.domain.models.UserDomainModel
import ru.alexsergeev.domain.repository.ChatsRepository
import ru.alexsergeev.domain.usecases.interfaces.GetAllUsersUseCase

internal class GetAllUsersUseCaseImpl(
    private val repository: ChatsRepository
) : GetAllUsersUseCase {
    override fun invoke(): Flow<List<UserDomainModel>> = repository.getAllUsers()
}