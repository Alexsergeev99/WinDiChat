package ru.alexsergeev.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.alexsergeev.domain.usecases.interfaces.GetAllMessagesUseCase
import ru.alexsergeev.domain.usecases.interfaces.GetAllUsersUseCase
import ru.alexsergeev.domain.usecases.interfaces.GetUserByIdUseCase
import ru.alexsergeev.domain.usecases.interfaces.GetUserProfileWithoutApiUseCase
import ru.alexsergeev.domain.usecases.interfaces.SendMessageUseCase
import ru.alexsergeev.presentation.models.FullName
import ru.alexsergeev.presentation.models.MessageUiModel
import ru.alexsergeev.presentation.models.Phone
import ru.alexsergeev.presentation.models.UserUiModel
import ru.alexsergeev.presentation.states.MessagesListState
import ru.alexsergeev.presentation.utils.mappers.DomainMessageToUiMessageMapper
import ru.alexsergeev.presentation.utils.mappers.DomainUserToUiUserMapper
import ru.alexsergeev.presentation.utils.mappers.UiMessageToDomainMessageMapper

internal class MessagesListViewModel(
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val getAllMessagesUseCase: GetAllMessagesUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val getUserProfileWithoutApiUseCase: GetUserProfileWithoutApiUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val domainUserToUiUserMapper: DomainUserToUiUserMapper,
    private val domainMessageToUiMessageMapper: DomainMessageToUiMessageMapper,
    private val uiMessageToDomainMessageMapper: UiMessageToDomainMessageMapper,
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

    private val _messages = MutableStateFlow<List<MessageUiModel>>(emptyList())
    private val messagesMutable = mutableListOf<MessageUiModel>()
    private val messages: StateFlow<List<MessageUiModel>> get() = _messages


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
            try {
                val messagesFlow = getAllMessagesUseCase.invoke()
                messagesFlow.collect { messages ->
                    messages.forEach { message ->
                        messagesMutable.add(domainMessageToUiMessageMapper.map(message))
                    }
                    _messages.emit(messagesMutable.toList())
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

    fun sendMessage(message: MessageUiModel) {
        viewModelScope.launch {
            messagesMutable.add(message)
            _messages.emit(messagesMutable.toList())
            _uiState.value = MessagesListState.Success(messages.value)
            sendMessageUseCase.invoke(uiMessageToDomainMessageMapper.map(message))
        }
    }


    fun getAllUsers(): StateFlow<List<UserUiModel>> = users
    fun getAllMessages(): StateFlow<List<MessageUiModel>> = messages

}