package ru.alexsergeev.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.alexsergeev.domain.models.UserDomainModel

interface ChatsRepository {
    fun getUserById(id: Int): Flow<UserDomainModel>
    fun getAllUsers(): Flow<List<UserDomainModel>>
}