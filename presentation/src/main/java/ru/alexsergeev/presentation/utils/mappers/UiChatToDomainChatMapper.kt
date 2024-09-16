package ru.alexsergeev.presentation.utils.mappers

import ru.alexsergeev.domain.models.ChatDomainModel
import ru.alexsergeev.presentation.models.ChatUiModel

internal class UiChatToDomainChatMapper : Mapper<ChatUiModel, ChatDomainModel> {
    override fun map(input: ChatUiModel): ChatDomainModel = with(input) {
        ChatDomainModel(
            id,
            firstUserId,
            secondUserId,
            messagesId
        )
    }
}