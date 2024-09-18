package ru.alexsergeev.domain.usecases.implementation

import kotlinx.coroutines.flow.Flow
import ru.alexsergeev.domain.repository.UserProfileRepository
import ru.alexsergeev.domain.usecases.interfaces.SendCodeUseCase

internal class SendCodeUseCaseImpl(
    private val repository: UserProfileRepository
) : SendCodeUseCase {
    override fun invoke(phone: String): Flow<Boolean> = repository.sendCode(phone)
}