package ru.alexsergeev.domain.usecases.interfaces

import kotlinx.coroutines.flow.Flow
import ru.alexsergeev.domain.models.UserDomainModel

interface GetUserByIdUseCase {
    fun invoke(id: Int): Flow<UserDomainModel>
}