package ru.alexsergeev.data.mock

import ru.alexsergeev.domain.models.MessageDomainModel

val messages = listOf(
    MessageDomainModel(id=1, senderId=2, recipientId=1, text="Жду", date="2023-11-09 16:44:11"),
    MessageDomainModel(id=2, senderId=1, recipientId=2, text="Ок", date="2023-11-09 16:43:11"),
    MessageDomainModel(id=3, senderId=2, recipientId=1, text="Подхожу", date="2023-11-09 16:42:11"),
    MessageDomainModel(id=4, senderId=1, recipientId=2, text="Ты где?", date="2023-11-09 16:41:11"),
    MessageDomainModel(id=5, senderId=2, recipientId=1, text="Давай", date="2023-11-09 16:40:11"),
    MessageDomainModel(id=6, senderId=1, recipientId=2, text="До встречи", date="2023-11-09 16:39:11"),
    MessageDomainModel(id=7, senderId=2, recipientId=1, text="Ок", date="2023-11-09 16:38:11"),
    MessageDomainModel(id=8, senderId=1, recipientId=2, text="Буду позже", date="2023-11-09 16:37:11"),
    MessageDomainModel(id=9, senderId=2, recipientId=1, text="Я заеду за тобой", date="2023-11-09 16:36:11"),
    MessageDomainModel(id=10, senderId=1, recipientId=2, text="Окей", date="2023-11-09 16:35:11"),
    MessageDomainModel(id=11, senderId=2, recipientId=1, text="А кто там?)", date="2023-11-09 16:34:11"),
    MessageDomainModel(id=12, senderId=1, recipientId=2, text=")))", date="2023-11-09 16:33:11"),
    MessageDomainModel(id=13, senderId=2, recipientId=1, text="Пойдем завтра гулять?", date="2023-11-09 16:32:11"),
    MessageDomainModel(id=14, senderId=1, recipientId=2, text="Как дела?", date="2023-11-09 16:31:11"),
    MessageDomainModel(id=15, senderId=2, recipientId=1, text="Привет", date="2023-11-09 16:30:11")
)