package ru.alexsergeev.domain.usecases.interfaces

import ru.alexsergeev.domain.models.UserDomainModel

interface UpdateUserProfileUseCase {
    suspend fun invoke(userDomainModel: UserDomainModel)
}

interface UpdateUserProfileInDatabaseUseCase {
    suspend fun invoke(userDomainModel: UserDomainModel)
}