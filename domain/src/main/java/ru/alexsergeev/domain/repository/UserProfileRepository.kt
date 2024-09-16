package ru.alexsergeev.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.alexsergeev.domain.models.UserDomainModel

interface UserProfileRepository {
    fun getUserData(): Flow<UserDomainModel>
    suspend fun setUserData(person: UserDomainModel)
    fun verifyCode(code: Int): Flow<Boolean>
}