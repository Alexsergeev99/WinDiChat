package ru.alexsergeev.presentation.utils.mappers

import ru.alexsergeev.domain.models.ChatDomainModel
import ru.alexsergeev.domain.models.MessageDomainModel
import ru.alexsergeev.presentation.models.ChatUiModel
import ru.alexsergeev.presentation.models.MessageUiModel

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

internal class DomainMessageToUiMessageMapper : Mapper<MessageDomainModel, MessageUiModel> {
    override fun map(input: MessageDomainModel): MessageUiModel = with(input) {
        MessageUiModel(
            id,
            senderId,
            recipientId,
            text,
            date
        )
    }
}

internal class UiMessageToDomainMessageMapper : Mapper<MessageUiModel, MessageDomainModel> {
    override fun map(input: MessageUiModel): MessageDomainModel = with(input) {
        MessageDomainModel(
            id,
            senderId,
            recipientId,
            text,
            date
        )
    }
}