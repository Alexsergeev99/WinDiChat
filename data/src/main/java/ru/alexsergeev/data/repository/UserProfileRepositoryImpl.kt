package ru.alexsergeev.data.repository

import android.content.Context
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import ru.alexsergeev.data.api.ApiService
import ru.alexsergeev.data.models.CodeRequest
import ru.alexsergeev.data.models.PhoneRequest
import ru.alexsergeev.data.models.RegisterRequest
import ru.alexsergeev.data.prefs.SharedPreferencesManager
import ru.alexsergeev.data.utils.DataUserToDomainUserMapper
import ru.alexsergeev.domain.models.FullName
import ru.alexsergeev.domain.models.Phone
import ru.alexsergeev.domain.models.UserDomainModel
import ru.alexsergeev.domain.repository.UserProfileRepository
import java.io.IOException

internal class UserProfileRepositoryImpl(
    private val apiService: ApiService,
    private val dataUserToDomainUserMapper: DataUserToDomainUserMapper,
    private val context: Context,
    private val sharedPreferencesManager: SharedPreferencesManager = SharedPreferencesManager(
        context
    )
) : UserProfileRepository {

    private val userDataMutable = MutableStateFlow(
        UserDomainModel(
            1,
            FullName("", ""),
            phone = Phone("+7", ""),
            "https://www.1zoom.me/big2/62/199578-yana.jpg",
        )
    )

    override fun getUserData(): Flow<UserDomainModel> = flow {
        try {
            val response = apiService.getUser(sharedPreferencesManager.getToken() ?: "")
            if (response.isSuccessful) {
                response.body()?.let { apiResponse ->
                    emit(dataUserToDomainUserMapper.map(apiResponse))
                }
            } else {
                Log.e("API Error", "Code: ${response.code()}, Message: ${response.message()}")
            }
        } catch (e: IOException) {
            Log.e("Network Error", "IOException: ${e.message}")
        } catch (e: Exception) {
            Log.e("Unknown Error", "Exception: ${e.message}")
        }
    }

    override fun getUserDataWithoutApi(): Flow<UserDomainModel> = flow {
        val user = userDataMutable.value
        emit(user)
    }


    override suspend fun setUserData(user: UserDomainModel) {
        userDataMutable.update { user }
    }

    override fun verifyCode(phone: String, code: String): Flow<Boolean> = flow {
        try {
            val response = apiService.verifyCode(CodeRequest(phone, code))
            response.body()?.let { sharedPreferencesManager.saveToken(it.access_token) }
            if (response.isSuccessful) {
                response.body()?.let { apiResponse ->
                    emit(apiResponse.is_user_exists)
                } ?: emit(false)
            } else {
                Log.e("API Error", "Code: ${response.code()}, Message: ${response.message()}")
                emit(false)
            }
        } catch (e: IOException) {
            Log.e("Network Error", "IOException: ${e.message}")
            emit(false)
        } catch (e: Exception) {
            Log.e("Unknown Error", "Exception: ${e.message}")
            emit(false)
        }
    }

    override fun sendCode(phone: String): Flow<Boolean> = flow {
        try {
            val response = apiService.sendCode(PhoneRequest(phone))
            if (response.isSuccessful) {
                response.body()?.let { apiResponse ->
                    emit(apiResponse.is_success)
                } ?: emit(false)
            } else {
                Log.e("API Error", "Code: ${response.code()}, Message: ${response.message()}")
                emit(false)
            }
        } catch (e: IOException) {
            Log.e("Network Error", "IOException: ${e.message}")
            emit(false)
        } catch (e: Exception) {
            Log.e("Unknown Error", "Exception: ${e.message}")
            emit(false)
        }
    }

    override suspend fun registerUser(phone: String, name: String, username: String) {
        try {
            val response = apiService.registerUser(RegisterRequest(phone, name, username))
            response.body()?.let { sharedPreferencesManager.saveToken(it.access_token) }
            if (!response.isSuccessful) {
                Log.e("API Error", "Code: ${response.code()}, Message: ${response.message()}")
            }
        } catch (e: IOException) {
            Log.e("Network Error", "IOException: ${e.message}")
        } catch (e: Exception) {
            Log.e("Unknown Error", "Exception: ${e.message}")
        }
    }
}