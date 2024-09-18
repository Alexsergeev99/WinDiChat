package ru.alexsergeev.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.alexsergeev.domain.usecases.interfaces.GetUserProfileWithoutApiUseCase
import ru.alexsergeev.domain.usecases.interfaces.SendCodeUseCase
import ru.alexsergeev.domain.usecases.interfaces.SetUserProfileUseCase
import ru.alexsergeev.presentation.models.FullName
import ru.alexsergeev.presentation.models.Phone
import ru.alexsergeev.presentation.models.UserUiModel
import ru.alexsergeev.presentation.utils.mappers.DomainUserToUiUserMapper
import ru.alexsergeev.presentation.utils.mappers.UiUserToDomainUserMapper

private const val PHONE_NUMBER_LENGTH = 10

internal class InputPhoneNumberViewModel(
    private val getUserProfileWithoutApiUseCase: GetUserProfileWithoutApiUseCase,
    private val domainUserToUiUserMapper: DomainUserToUiUserMapper,
    private val setUserProfileUseCase: SetUserProfileUseCase,
    private val sendCodeUseCase: SendCodeUseCase,
    private val uiUserToDomainUserMapper: UiUserToDomainUserMapper
) : ViewModel() {

    private val sendCodeStatusMutable = MutableStateFlow<Boolean>(false)
    private val sendCodeStatus: StateFlow<Boolean> = sendCodeStatusMutable

    private val userDataMutable = MutableStateFlow(
        UserUiModel(
            id = 1,
            name = FullName(
                firstName = "",
                secondName = ""
            ),
            phone = Phone(
                countryCode = "",
                basicNumber = ""
            ),
            avatar = "",
        )
    )
    private val userData: StateFlow<UserUiModel> = userDataMutable

    fun getUserData(): StateFlow<UserUiModel> {
        try {
            viewModelScope.launch {
                val user = getUserProfileWithoutApiUseCase.invoke().last()
                userDataMutable.update { domainUserToUiUserMapper.map(user) }
            }
            return userData
        } catch (e: Exception) {
            throw e
        }
    }

    fun setUserData(userUiModel: UserUiModel) {
        try {
            viewModelScope.launch {
                setUserProfileUseCase.invoke(uiUserToDomainUserMapper.map(userUiModel))
                userDataMutable.update { userUiModel }
            }
        } catch (e: Exception) {
            throw e
        }
    }

    fun sendCodeAndUpdateStatus(phone: String): Boolean {
        viewModelScope.launch {
            async {
                sendCodeUseCase.invoke(phone)
                    .catch { e ->
                        Log.e("SendCodeError", "Exception: ${e.message}")
                        emit(false)
                    }
                    .collect { result ->
                        sendCodeStatusMutable.value = result
                    }
            }.await()
        }
        return sendCodeStatus.value
    }

    fun getSendCodeStatus() = sendCodeStatus

    fun checkPhoneLength(length: Int): Boolean = length == PHONE_NUMBER_LENGTH
}

