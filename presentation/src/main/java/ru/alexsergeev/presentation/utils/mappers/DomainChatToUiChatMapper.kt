package ru.alexsergeev.presentation.utils.mappers

import ru.alexsergeev.domain.models.ChatDomainModel
import ru.alexsergeev.presentation.models.ChatUiModel

internal class DomainChatToUiChatMapper : Mapper<ChatDomainModel, ChatUiModel> {
    override fun map(input: ChatDomainModel): ChatUiModel = with(input) {
        ChatUiModel(
            id,
            firstUserId,
            secondUserId,
            messagesId
        )
    }
}