package ru.alexsergeev.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.alexsergeev.domain.usecases.interfaces.GetUserProfileUseCase
import ru.alexsergeev.domain.usecases.interfaces.GetUserProfileWithoutApiUseCase
import ru.alexsergeev.domain.usecases.interfaces.RegisterUserUseCase
import ru.alexsergeev.domain.usecases.interfaces.SetUserProfileUseCase
import ru.alexsergeev.domain.usecases.interfaces.UpdateUserProfileUseCase
import ru.alexsergeev.presentation.models.FullName
import ru.alexsergeev.presentation.models.Phone
import ru.alexsergeev.presentation.models.UserUiModel
import ru.alexsergeev.presentation.utils.mappers.DomainUserToUiUserMapper
import ru.alexsergeev.presentation.utils.mappers.UiUserToDomainUserMapper

internal class UserProfileViewModel(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val getUserProfileWithoutApiUseCase: GetUserProfileWithoutApiUseCase,
    private val domainUserToUiUserMapper: DomainUserToUiUserMapper,
    private val setUserProfileUseCase: SetUserProfileUseCase,
    private val updateUserProfileUseCase: UpdateUserProfileUseCase,
    private val registerUserUseCase: RegisterUserUseCase,
    private val uiUserToDomainUserMapper: UiUserToDomainUserMapper
) : ViewModel() {

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

    private val userDataWithoutApiMutable = MutableStateFlow(
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
    private val userDataWithoutApi: StateFlow<UserUiModel> = userDataWithoutApiMutable

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

    fun setUserData(userUiModel: UserUiModel) {
        try {
            viewModelScope.launch {
                setUserProfileUseCase.invoke(uiUserToDomainUserMapper.map(userUiModel))
                updateUserProfileUseCase.invoke(uiUserToDomainUserMapper.map(userUiModel))
                userDataMutable.update { userUiModel }
            }
        } catch (e: Exception) {
            throw e
        }
    }

    fun registerUser(userUiModel: UserUiModel) {
        try {
            viewModelScope.launch {
                registerUserUseCase.invoke(
                    phone = "${userUiModel.phone.countryCode}${userUiModel.phone.basicNumber}",
                    name = "${userUiModel.name.firstName} ${userUiModel.name.secondName}",
                    username = userUiModel.username
                )
            }
        } catch (e: Exception) {
            throw e
        }
    }
}