package ru.alexsergeev.domain.usecases.interfaces

import kotlinx.coroutines.flow.Flow

interface SendCodeUseCase {
    fun invoke(phone: String): Flow<Boolean>
}