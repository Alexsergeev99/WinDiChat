package ru.alexsergeev.presentation.states

import ru.alexsergeev.presentation.models.ChatUiModel

internal sealed class MainScreenState {
    data object Loading : MainScreenState()
    data class Success(val chats: List<ChatUiModel>) : MainScreenState()
    data class Error(val message: String) : MainScreenState()
}