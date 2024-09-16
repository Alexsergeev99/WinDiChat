package ru.alexsergeev.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.alexsergeev.domain.usecases.interfaces.GetAllUsersUseCase
import ru.alexsergeev.domain.usecases.interfaces.GetUserByIdUseCase
import ru.alexsergeev.presentation.models.FullName
import ru.alexsergeev.presentation.models.Phone
import ru.alexsergeev.presentation.models.UserUiModel
import ru.alexsergeev.presentation.utils.mappers.DomainUserToUiUserMapper

internal class MainScreenViewModel(
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val domainUserToUiUserMapper: DomainUserToUiUserMapper,
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

    private val usersMutable = MutableStateFlow<MutableList<UserUiModel>>(mutableListOf())
    private val users: StateFlow<List<UserUiModel>> = usersMutable

    init {
        getAllUsersFlow()
    }

    private fun getAllUsersFlow() {
        try {
            viewModelScope.launch {
                val eventsFlow = getAllUsersUseCase.invoke()
                eventsFlow.collect { events ->
                    events.forEach { event ->
                        usersMutable.value.add(domainUserToUiUserMapper.map(event))
                    }
                }
            }
        } catch (e: Exception) {
            throw e
        }
    }

    fun getUserById(id: Int): StateFlow<UserUiModel> {
        try {
            viewModelScope.launch {
                val user = getUserByIdUseCase.invoke(id).first()
                userDataMutable.update { domainUserToUiUserMapper.map(user) }
            }
            return userData
        } catch (e: Exception) {
            throw e
        }
    }

    fun getAllUsers(): StateFlow<List<UserUiModel>> = users

}