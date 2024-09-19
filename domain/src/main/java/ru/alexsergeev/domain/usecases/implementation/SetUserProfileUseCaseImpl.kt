package ru.alexsergeev.domain.usecases.implementation

import ru.alexsergeev.domain.models.UserDomainModel
import ru.alexsergeev.domain.repository.UserProfileRepository
import ru.alexsergeev.domain.usecases.interfaces.SetUserProfileUseCase
import ru.alexsergeev.domain.usecases.interfaces.UpdateUserProfileUseCase

internal class SetUserProfileUseCaseImpl(private val repository: UserProfileRepository) :
    SetUserProfileUseCase {
    override suspend fun invoke(userDomainModel: UserDomainModel) =
        repository.setUserData(userDomainModel)
}

internal class UpdateUserProfileUseCaseImpl(private val repository: UserProfileRepository) :
    UpdateUserProfileUseCase {
    override suspend fun invoke(userDomainModel: UserDomainModel) =
        repository.updateUser(userDomainModel)
}