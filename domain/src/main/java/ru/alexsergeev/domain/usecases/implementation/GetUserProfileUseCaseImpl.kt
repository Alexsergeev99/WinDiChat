package ru.alexsergeev.domain.usecases.implementation

import kotlinx.coroutines.flow.Flow
import ru.alexsergeev.domain.models.UserDomainModel
import ru.alexsergeev.domain.repository.UserProfileRepository
import ru.alexsergeev.domain.usecases.interfaces.GetUserProfileUseCase

internal class GetUserProfileUseCaseImpl(private val repository: UserProfileRepository) :
    GetUserProfileUseCase {
    override fun invoke(): Flow<UserDomainModel> = repository.getUserData()
}