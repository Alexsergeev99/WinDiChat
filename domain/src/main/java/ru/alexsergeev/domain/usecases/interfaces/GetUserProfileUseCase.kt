package ru.alexsergeev.domain.usecases.interfaces

import kotlinx.coroutines.flow.Flow
import ru.alexsergeev.domain.models.UserDomainModel

interface GetUserProfileUseCase {
    fun invoke(): Flow<UserDomainModel>
}