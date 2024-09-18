package ru.alexsergeev.domain.usecases.interfaces

import kotlinx.coroutines.flow.Flow

interface ValidateCodeUseCase {
    fun invoke(phone: String, code: String): Flow<Boolean>
}