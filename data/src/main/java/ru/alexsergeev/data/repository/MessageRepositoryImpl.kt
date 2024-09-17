package ru.alexsergeev.data.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import ru.alexsergeev.data.mock.messages
import ru.alexsergeev.domain.models.MessageDomainModel
import ru.alexsergeev.domain.repository.MessageRepository

internal class MessageRepositoryImpl : MessageRepository {

    private val cacheMessagesFlow = MutableStateFlow<List<MessageDomainModel>>(mutableListOf())

    @OptIn(ExperimentalCoroutinesApi::class)
    private val cacheMessages = cacheMessagesFlow.flatMapLatest {
        flow {
//            if (cacheMessagesFlow.value.isEmpty()) {
            fetchMessages()
//            }
            emit(it)
        }
    }

    private suspend fun fetchMessages() {
        cacheMessagesFlow.value = messages
    }

    override fun getAllMessages(): Flow<List<MessageDomainModel>> =
//        cacheMessages
        flow {
        emit(messages)
    }

    override suspend fun sendMessage(message: MessageDomainModel) {
        messages.add(message)
        fetchMessages()
    }
}