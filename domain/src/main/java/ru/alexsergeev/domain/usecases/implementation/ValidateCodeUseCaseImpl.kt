package ru.alexsergeev.domain.usecases.implementation

import kotlinx.coroutines.flow.Flow
import ru.alexsergeev.domain.repository.UserProfileRepository
import ru.alexsergeev.domain.usecases.interfaces.ValidateCodeUseCase

internal class ValidateCodeUseCaseImpl(private val repository: UserProfileRepository) :
    ValidateCodeUseCase {
    override fun invoke(code: Int): Flow<Boolean> = repository.verifyCode(code)
}