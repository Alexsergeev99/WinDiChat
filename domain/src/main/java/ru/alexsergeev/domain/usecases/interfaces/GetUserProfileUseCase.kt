package ru.alexsergeev.domain.usecases.interfaces

import kotlinx.coroutines.flow.Flow
import ru.alexsergeev.domain.models.UserDomainModel

interface GetUserProfileUseCase {
    fun invoke(phone: String): Flow<UserDomainModel>
}

interface GetUserProfileWithoutApiUseCase {
    fun invoke(): Flow<UserDomainModel>
}

interface GetBasicNumberUseCase {
    fun invoke(): Flow<String>
}