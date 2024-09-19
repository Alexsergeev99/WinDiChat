package ru.alexsergeev.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.alexsergeev.domain.models.UserDomainModel

interface UserProfileRepository {
    fun getUserData(phone: String): Flow<UserDomainModel>
    fun getBasicNumber(): Flow<String>
    fun getUserDataWithoutApi(): Flow<UserDomainModel>
    suspend fun setUserData(user: UserDomainModel)
    suspend fun setBasicNumber(phone: String)
    fun verifyCode(phone: String, code: String): Flow<Boolean>
    fun sendCode(phone: String): Flow<Boolean>
    suspend fun refreshToken()
    suspend fun registerUser(phone: String, name: String, username: String)
    suspend fun updateUser(user: UserDomainModel)
    suspend fun updateUserInDatabase(user: UserDomainModel)

}