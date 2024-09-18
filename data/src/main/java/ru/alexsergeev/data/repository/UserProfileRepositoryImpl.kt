package ru.alexsergeev.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import ru.alexsergeev.data.api.ApiService
import ru.alexsergeev.data.models.PhoneRequest
import ru.alexsergeev.domain.models.FullName
import ru.alexsergeev.domain.models.Phone
import ru.alexsergeev.domain.models.UserDomainModel
import ru.alexsergeev.domain.repository.UserProfileRepository
import java.io.IOException

internal class UserProfileRepositoryImpl(
    private val apiService: ApiService,
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
        val person = userDataMutable.value
        emit(person)
    }

    override suspend fun setUserData(user: UserDomainModel) {
        userDataMutable.update { user }
    }

    override fun verifyCode(code: Int): Flow<Boolean> = flow {
        val trueCode = code == 133337
        emit(trueCode)
    }

//    override fun sendCode(phone: String): Flow<Boolean> = flow {
//        try {
//            val response = apiService.sendCode(PhoneRequest(phone))
//            if (!response.isSuccessful) {
//                throw ApiError(response.code(), response.message())
//            } else {
//                response.body()?.let { apiResponse ->
//                    if (apiResponse.is_success) {
//                        emit(true)
//                    } else {
//                        emit(false)
//                    }
//                }
//            }
//        } catch (e: IOException) {
//            Log.d("error", "networkError")
//        } catch (e: Exception) {
//            throw UnknownError
//        }
//    }

    override fun sendCode(phone: String): Flow<Boolean> = flow {
        try {
            val response = apiService.sendCode(PhoneRequest(phone))

            if (response.isSuccessful) {
                response.body()?.let { apiResponse ->
                    emit(apiResponse.is_success)
                } ?: emit(false) // Если тело ответа null, возвращаем false
            } else {
                Log.e("API Error", "Code: ${response.code()}, Message: ${response.message()}")
                emit(false) // Возвращаем false при ошибке API
            }
        } catch (e: IOException) {
            Log.e("Network Error", "IOException: ${e.message}")
            emit(false) // Возвращаем false при сетевой ошибке
        } catch (e: Exception) {
            Log.e("Unknown Error", "Exception: ${e.message}")
            emit(false) // Возвращаем false при неизвестной ошибке
        }
    }
}