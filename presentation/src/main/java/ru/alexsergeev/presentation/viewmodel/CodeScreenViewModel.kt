package ru.alexsergeev.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.alexsergeev.domain.usecases.interfaces.GetUserProfileWithoutApiUseCase
import ru.alexsergeev.domain.usecases.interfaces.ValidateCodeUseCase
import ru.alexsergeev.presentation.models.FullName
import ru.alexsergeev.presentation.models.Phone
import ru.alexsergeev.presentation.models.UserUiModel
import ru.alexsergeev.presentation.utils.mappers.DomainUserToUiUserMapper

internal class CodeScreenViewModel(
    private val validateCodeUseCase: ValidateCodeUseCase,
    private val getUserProfileWithoutApiUseCase: GetUserProfileWithoutApiUseCase,
    private val domainUserToUiUserMapper: DomainUserToUiUserMapper,
) : ViewModel() {

    private val codeIsValidMutable = MutableStateFlow<Boolean>(false)
    private val codeIsValid: StateFlow<Boolean> = codeIsValidMutable

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
    fun validateCode(): StateFlow<Boolean> = codeIsValid
    fun validateCodeFlow(phone: String, code: String): Boolean {
        viewModelScope.launch {
            validateCodeUseCase.invoke(phone, code)
                .catch { e ->
                    Log.e("SendCodeError", "Exception: ${e.message}")
                    emit(false)
                }
                .collect { result ->
                    codeIsValidMutable.value = result
                }
        }
        return codeIsValid.value
    }

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
}