package ru.alexsergeev.data.mock

import ru.alexsergeev.domain.models.ChatDomainModel

val chats = listOf(
    ChatDomainModel(id = 1, 1, 2, listOf(1,2,3)),
    ChatDomainModel(id = 2, 1, 12, listOf(4,5,6)),
    ChatDomainModel(id = 3, 1, 22, listOf(7,8,9)),
    ChatDomainModel(id = 4, 1, 3, listOf(10,11,12)),
    ChatDomainModel(id = 5, 1, 23, listOf(13,14,15)),
)