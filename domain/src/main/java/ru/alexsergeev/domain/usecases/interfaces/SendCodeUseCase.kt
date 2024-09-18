package ru.alexsergeev.domain.usecases.interfaces

import kotlinx.coroutines.flow.Flow

interface SendCodeUseCase {
    fun invoke(phone: String): Flow<Boolean>
}

interface RegisterUserUseCase {
    suspend fun invoke(phone: String, name: String, username: String)
}