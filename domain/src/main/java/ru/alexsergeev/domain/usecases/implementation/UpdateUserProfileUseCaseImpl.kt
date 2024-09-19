package ru.alexsergeev.domain.usecases.implementation

import ru.alexsergeev.domain.models.UserDomainModel
import ru.alexsergeev.domain.repository.UserProfileRepository
import ru.alexsergeev.domain.usecases.interfaces.UpdateUserProfileInDatabaseUseCase
import ru.alexsergeev.domain.usecases.interfaces.UpdateUserProfileUseCase

internal class UpdateUserProfileUseCaseImpl(private val repository: UserProfileRepository) :
    UpdateUserProfileUseCase {
    override suspend fun invoke(userDomainModel: UserDomainModel) =
        repository.updateUser(userDomainModel)
}

internal class UpdateUserProfileInDatabaseUseCaseImpl(private val repository: UserProfileRepository) :
    UpdateUserProfileInDatabaseUseCase {
    override suspend fun invoke(userDomainModel: UserDomainModel) =
        repository.updateUserInDatabase(userDomainModel)
}

