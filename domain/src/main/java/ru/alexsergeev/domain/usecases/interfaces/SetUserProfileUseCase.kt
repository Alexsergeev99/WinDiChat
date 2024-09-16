package ru.alexsergeev.domain.usecases.interfaces

import ru.alexsergeev.domain.models.UserDomainModel

interface SetUserProfileUseCase {
    suspend fun invoke(userDomainModel: UserDomainModel)
}