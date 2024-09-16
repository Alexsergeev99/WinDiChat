package ru.alexsergeev.domain.usecases.implementation

import ru.alexsergeev.domain.models.UserDomainModel
import ru.alexsergeev.domain.repository.UserProfileRepository
import ru.alexsergeev.domain.usecases.interfaces.SetUserProfileUseCase

internal class SetUserProfileUseCaseImpl(private val repository: UserProfileRepository) :
    SetUserProfileUseCase {
    override suspend fun invoke(userDomainModel: UserDomainModel) =
        repository.setUserData(userDomainModel)
}