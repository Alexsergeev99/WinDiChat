package ru.alexsergeev.presentation.states

import ru.alexsergeev.presentation.models.MessageUiModel

internal sealed class MessagesListState {
    data object Loading : MessagesListState()
    data class Success(val messages: List<MessageUiModel>) : MessagesListState()
    data class Error(val message: String) : MessagesListState()
}