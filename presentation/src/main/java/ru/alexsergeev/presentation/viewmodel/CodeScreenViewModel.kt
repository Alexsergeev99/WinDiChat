package ru.alexsergeev.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.alexsergeev.domain.usecases.interfaces.GetUserProfileUseCase
import ru.alexsergeev.domain.usecases.interfaces.ValidateCodeUseCase
import ru.alexsergeev.presentation.models.FullName
import ru.alexsergeev.presentation.models.Phone
import ru.alexsergeev.presentation.models.UserUiModel
import ru.alexsergeev.presentation.utils.mappers.DomainUserToUiUserMapper

internal class CodeScreenViewModel(
    private val validateCodeUseCase: ValidateCodeUseCase,
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val domainUserToUiUserMapper: DomainUserToUiUserMapper,
) : ViewModel() {

    private val codeIsValidMutable = MutableStateFlow<Boolean>(false)
    private val codeIsValid: StateFlow<Boolean> = codeIsValidMutable

    private val userDataMutable = MutableStateFlow(
        UserUiModel(
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
    fun validateCodeFlow(code: Int) {
        try {
            viewModelScope.launch {
                codeIsValidMutable.value = validateCodeUseCase.invoke(code).last()
            }
        } catch (e: Exception) {
            throw e
        }
    }

    fun getUserData(): StateFlow<UserUiModel> {
        try {
            viewModelScope.launch {
                val user = getUserProfileUseCase.invoke().last()
                userDataMutable.update { domainUserToUiUserMapper.map(user) }
            }
            return userData
        } catch (e: Exception) {
            throw e
        }
    }
}