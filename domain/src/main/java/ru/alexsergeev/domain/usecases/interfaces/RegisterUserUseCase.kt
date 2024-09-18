package ru.alexsergeev.domain.usecases.interfaces

interface RegisterUserUseCase {
    suspend fun invoke(phone: String, name: String, username: String)
}