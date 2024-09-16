package ru.alexsergeev.data.mock

import ru.alexsergeev.domain.models.MessageDomainModel

val messages = listOf(
    MessageDomainModel(id=1, senderId=2, recipientId=1, text="Сообщение 1", date="2023-11-09 16:44:11"),
    MessageDomainModel(id=2, senderId=1, recipientId=2, text="Сообщение 2", date="2023-11-09 16:43:11"),
    MessageDomainModel(id=3, senderId=2, recipientId=1, text="Сообщение 3", date="2023-11-09 16:42:11"),
    MessageDomainModel(id=4, senderId=1, recipientId=2, text="Сообщение 4", date="2023-11-09 16:41:11"),
    MessageDomainModel(id=5, senderId=2, recipientId=1, text="Сообщение 5", date="2023-11-09 16:40:11"),
    MessageDomainModel(id=6, senderId=1, recipientId=2, text="Сообщение 6", date="2023-11-09 16:39:11"),
    MessageDomainModel(id=7, senderId=2, recipientId=1, text="Сообщение 7", date="2023-11-09 16:38:11"),
    MessageDomainModel(id=8, senderId=1, recipientId=2, text="Сообщение 8", date="2023-11-09 16:37:11"),
    MessageDomainModel(id=9, senderId=2, recipientId=1, text="Сообщение 9", date="2023-11-09 16:36:11"),
    MessageDomainModel(id=10, senderId=1, recipientId=2, text="Сообщение 10", date="2023-11-09 16:35:11"),
    MessageDomainModel(id=11, senderId=2, recipientId=1, text="Сообщение 11", date="2023-11-09 16:34:11"),
    MessageDomainModel(id=12, senderId=1, recipientId=2, text="Сообщение 12", date="2023-11-09 16:33:11"),
    MessageDomainModel(id=13, senderId=2, recipientId=1, text="Сообщение 13", date="2023-11-09 16:32:11"),
    MessageDomainModel(id=14, senderId=1, recipientId=2, text="Сообщение 14", date="2023-11-09 16:31:11"),
    MessageDomainModel(id=15, senderId=2, recipientId=1, text="Сообщение 15", date="2023-11-09 16:30:11")
)