package ru.alexsergeev.domain.usecases.implementation

import ru.alexsergeev.domain.repository.UserProfileRepository
import ru.alexsergeev.domain.usecases.interfaces.RegisterUserUseCase

internal class RegisterUserUseCaseImpl(
    private val repository: UserProfileRepository
) : RegisterUserUseCase {
    override suspend fun invoke(phone: String, name: String, username: String) = repository.registerUser(phone, name, username)
}
