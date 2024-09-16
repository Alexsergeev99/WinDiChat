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
import ru.alexsergeev.domain.usecases.interfaces.GetAllChatsUseCase
import ru.alexsergeev.domain.usecases.interfaces.GetAllUsersUseCase
import ru.alexsergeev.domain.usecases.interfaces.GetUserByIdUseCase
import ru.alexsergeev.presentation.models.ChatUiModel
import ru.alexsergeev.presentation.models.FullName
import ru.alexsergeev.presentation.models.Phone
import ru.alexsergeev.presentation.models.UserUiModel
import ru.alexsergeev.presentation.states.MainScreenState
import ru.alexsergeev.presentation.utils.mappers.DomainChatToUiChatMapper
import ru.alexsergeev.presentation.utils.mappers.DomainUserToUiUserMapper

internal class MainScreenViewModel(
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val getAllChatsUseCase: GetAllChatsUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val domainUserToUiUserMapper: DomainUserToUiUserMapper,
    private val domainChatToUiChatMapper: DomainChatToUiChatMapper,
) : ViewModel() {

    private val _uiState = MutableStateFlow<MainScreenState>(MainScreenState.Loading)
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

    private val chatsMutable = MutableStateFlow<MutableList<ChatUiModel>>(mutableListOf())
    private val chats: StateFlow<List<ChatUiModel>> = chatsMutable

    init {
        getAllUsersFlow()
        getAllChatsFlow()
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

    private fun getAllChatsFlow() {
        viewModelScope.launch {
            _uiState.value = MainScreenState.Loading
            delay(1000)
            try {
                val chatsFlow = getAllChatsUseCase.invoke()
                chatsFlow.collect { chats ->
                    chats.forEach { chat ->
                        chatsMutable.value.add(domainChatToUiChatMapper.map(chat))
                    }
                }
                if (chats.value.isEmpty()) {
                    _uiState.value = MainScreenState.Error("Exception")
                } else {
                    _uiState.value = MainScreenState.Success(chats.value)
                }
            } catch (e: Exception) {
                _uiState.value = MainScreenState.Error("Exception")
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
    fun getAllChats(): StateFlow<List<ChatUiModel>> = chats

}