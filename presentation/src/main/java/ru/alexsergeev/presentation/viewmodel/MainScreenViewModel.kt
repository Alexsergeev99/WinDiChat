package ru.alexsergeev.presentation.viewmodel

import android.util.Log
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
import ru.alexsergeev.domain.usecases.interfaces.GetAllMessagesUseCase
import ru.alexsergeev.domain.usecases.interfaces.GetAllUsersUseCase
import ru.alexsergeev.domain.usecases.interfaces.GetUserByIdUseCase
import ru.alexsergeev.presentation.models.ChatUiModel
import ru.alexsergeev.presentation.models.FullName
import ru.alexsergeev.presentation.models.MessageUiModel
import ru.alexsergeev.presentation.models.Phone
import ru.alexsergeev.presentation.models.UserUiModel
import ru.alexsergeev.presentation.states.MainScreenState
import ru.alexsergeev.presentation.utils.mappers.DomainChatToUiChatMapper
import ru.alexsergeev.presentation.utils.mappers.DomainMessageToUiMessageMapper
import ru.alexsergeev.presentation.utils.mappers.DomainUserToUiUserMapper
import java.util.Locale

internal class MainScreenViewModel(
    private val getAllUsersUseCase: GetAllUsersUseCase,
    private val getAllChatsUseCase: GetAllChatsUseCase,
    private val getAllMessagesUseCase: GetAllMessagesUseCase,
    private val getUserByIdUseCase: GetUserByIdUseCase,
    private val domainUserToUiUserMapper: DomainUserToUiUserMapper,
    private val domainChatToUiChatMapper: DomainChatToUiChatMapper,
    private val domainMessageToUiMessageMapper: DomainMessageToUiMessageMapper,
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

    private val messagesMutable = MutableStateFlow<MutableList<MessageUiModel>>(mutableListOf())
    private val messages: StateFlow<List<MessageUiModel>> = messagesMutable

    private val filteredChatsMutable = MutableStateFlow<MutableList<ChatUiModel>>(mutableListOf())
    private val filteredChats: StateFlow<List<ChatUiModel>> = filteredChatsMutable

    private val searchedTextMutable = MutableStateFlow<String>("")
    private val searchedText: StateFlow<String> = searchedTextMutable

    private val isLoadingMutable = MutableStateFlow(false)
    private val isLoading = isLoadingMutable.asStateFlow()

    init {
        getAllUsersFlow()
        getAllChatsFlow()
        getAllMessagesFlow()
        setFilteredChatsList()
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
            try {
                val chatsFlow = getAllChatsUseCase.invoke()
                chatsFlow.collect { chats ->
                    chats.forEach { chat ->
                        chatsMutable.value.add(domainChatToUiChatMapper.map(chat))
                    }
                }
            } catch (e: Exception) {
                throw e
            }
        }
    }

    fun loadStuffAfterSwipe() {
        try {
            viewModelScope.launch {
                isLoadingMutable.value = true
                delay(2000L)
                setFilteredChatsList()
                isLoadingMutable.value = false
            }
        } catch (e: Exception) {
            throw e
        }
    }

    fun setFilteredChatsList() {
        viewModelScope.launch {
            _uiState.value = MainScreenState.Loading
            delay(1000)
            try {
                val resultList = mutableListOf<ChatUiModel>()
                chats.value.forEach { chat ->
                    if (getUserById(chat.secondUserId).value.name.firstName.lowercase(Locale.getDefault())
                            .contains(searchedText.value.lowercase(Locale.getDefault())) ||
                        getUserById(chat.secondUserId).value.name.secondName.lowercase(Locale.getDefault())
                            .contains(searchedText.value.lowercase(Locale.getDefault()))
                    ) {
                        resultList.add(chat)
                    }
                }

                filteredChatsMutable.value = if (searchedText.value.isEmpty()) {
                    chats.value.toMutableList()
                } else {
                    resultList
                }

                if (resultList.isEmpty()) {
                    _uiState.value = MainScreenState.EmptyList
                } else {
                    _uiState.value = MainScreenState.Success(filteredChats.value)
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

    private fun getAllMessagesFlow() {
        viewModelScope.launch {
            try {
                val messagesFlow = getAllMessagesUseCase.invoke()
                messagesFlow.collect { messages ->
                    messages.forEach { message ->
                        messagesMutable.value.add(domainMessageToUiMessageMapper.map(message))
                    }
                }
            } catch (e: Exception) {
                throw e
            }
        }
    }

    fun setSearchText(text: String) {
        searchedTextMutable.value = text
    }

    fun getAllUsers(): StateFlow<List<UserUiModel>> = users
    fun getAllChats(): StateFlow<List<ChatUiModel>> = chats
    fun getFilteredChatList(): StateFlow<List<ChatUiModel>> = filteredChats
    fun getAllMessages(): StateFlow<List<MessageUiModel>> = messages
    fun isLoading() = isLoading


}