package ru.alexsergeev.domain.usecases.implementation

import kotlinx.coroutines.flow.Flow
import ru.alexsergeev.domain.models.UserDomainModel
import ru.alexsergeev.domain.repository.UserProfileRepository
import ru.alexsergeev.domain.usecases.interfaces.GetBasicNumberUseCase
import ru.alexsergeev.domain.usecases.interfaces.GetUserProfileUseCase
import ru.alexsergeev.domain.usecases.interfaces.GetUserProfileWithoutApiUseCase

internal class GetUserProfileUseCaseImpl(private val repository: UserProfileRepository) :
    GetUserProfileUseCase {
    override fun invoke(phone: String): Flow<UserDomainModel> = repository.getUserData(phone)
}

internal class GetUseCaseUserProfileWithoutApiImpl(private val repository: UserProfileRepository) :
    GetUserProfileWithoutApiUseCase {
    override fun invoke(): Flow<UserDomainModel> = repository.getUserDataWithoutApi()
}

internal class GetBasicNumberUseCaseImpl(private val repository: UserProfileRepository) :
    GetBasicNumberUseCase {
    override fun invoke(): Flow<String> = repository.getBasicNumber()
}
