package ru.alexsergeev.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.alexsergeev.domain.usecases.interfaces.GetAllMessagesUseCase
import ru.alexsergeev.domain.usecases.interfaces.GetAllUsersUseCase
import ru.alexsergeev.domain.usecases.interfaces.GetUserByIdUseCase
import ru.alexsergeev.presentation.models.FullName
import ru.alexsergeev.presentation.models.MessageUiModel
import ru.alexsergeev.presentation.models.Phone
import ru.alexsergeev.presentation.models.UserUiModel
import ru.alexsergeev.presentation.states.MessagesListState
import ru.alexsergeev.presentation.utils.mappers.DomainMessageToUiMessageMapper
import ru.alexsergeev.presentation.utils.mappers.DomainUserToUiUserMapper

internal class MessagesListViewModel(
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val getAllMessagesUseCase: GetAllMessagesUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val domainUserToUiUserMapper: DomainUserToUiUserMapper,
    private val domainMessageToUiMessageMapper: DomainMessageToUiMessageMapper,
) : ViewModel() {

    private val _uiState = MutableStateFlow<MessagesListState>(MessagesListState.Loading)
    val uiState = _uiState.asStateFlow()

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

    private val messagesMutable = MutableStateFlow<MutableList<MessageUiModel>>(mutableListOf())
    private val messages: StateFlow<List<MessageUiModel>> = messagesMutable

    init {
        getAllUsersFlow()
        getAllMessagesFlow()
    }

    private fun getAllUsersFlow() {
        try {
            viewModelScope.launch {
                val usersFlow = getAllUsersUseCase.invoke()
                usersFlow.collect { users ->
                    users.forEach { user ->
                        usersMutable.value.add(domainUserToUiUserMapper.map(user))
                    }
                }
            }
        } catch (e: Exception) {
            throw e
        }
    }

    private fun getAllMessagesFlow() {
        viewModelScope.launch {
            _uiState.value = MessagesListState.Loading
            delay(1000)
            try {
                val messagesFlow = getAllMessagesUseCase.invoke()
                messagesFlow.collect { messages ->
                    messages.forEach { message ->
                        messagesMutable.value.add(domainMessageToUiMessageMapper.map(message))
                    }
                }
                if (messages.value.isEmpty()) {
                    _uiState.value = MessagesListState.Error("Exception")
                } else {
                    _uiState.value = MessagesListState.Success(messages.value)
                }
            } catch (e: Exception) {
                _uiState.value = MessagesListState.Error("Exception")
            }
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
    fun getAllMessages(): StateFlow<List<MessageUiModel>> = messages

}