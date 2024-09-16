package ru.alexsergeev.domain.usecases.interfaces

import kotlinx.coroutines.flow.Flow

interface ValidateCodeUseCase {
    fun invoke(code: Int): Flow<Boolean>
}