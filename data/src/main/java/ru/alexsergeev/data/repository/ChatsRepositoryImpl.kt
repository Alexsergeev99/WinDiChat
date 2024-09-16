package ru.alexsergeev.data.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import ru.alexsergeev.data.mock.chats
import ru.alexsergeev.data.mock.usersList
import ru.alexsergeev.domain.models.ChatDomainModel
import ru.alexsergeev.domain.models.UserDomainModel
import ru.alexsergeev.domain.repository.ChatsRepository

internal class ChatsRepositoryImpl : ChatsRepository {

    private val cacheUsersFlow = MutableStateFlow<List<UserDomainModel>>(mutableListOf())

    @OptIn(ExperimentalCoroutinesApi::class)
    private val cacheUsers = cacheUsersFlow.flatMapLatest {
        flow {
            if (cacheUsersFlow.value.isEmpty()) {
                fetchUsers()
            }
            emit(it)
        }
    }

    private val cacheChatsFlow = MutableStateFlow<List<ChatDomainModel>>(mutableListOf())

    @OptIn(ExperimentalCoroutinesApi::class)
    private val cacheChats = cacheChatsFlow.flatMapLatest {
        flow {
            if (cacheChatsFlow.value.isEmpty()) {
                fetchChats()
            }
            emit(it)
        }
    }

    private suspend fun fetchUsers() {
        cacheUsersFlow.value = usersList
    }

    private suspend fun fetchChats() {
        cacheChatsFlow.value = chats
    }

    override fun getUserById(id: Int): Flow<UserDomainModel> =
        flow {
            getAllUsers().collect { users ->
                val user = users.find { id == it.id } ?: throw Exception()
                emit(user)
            }
        }

    override fun getAllUsers(): Flow<List<UserDomainModel>> = cacheUsers
    override fun getAllChats(): Flow<List<ChatDomainModel>> = flow {
        emit(chats)
    }

}