package ru.alexsergeev.data.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.alexsergeev.data.repository.ChatsRepositoryImpl
import ru.alexsergeev.data.repository.MessageRepositoryImpl
import ru.alexsergeev.data.repository.UserProfileRepositoryImpl
import ru.alexsergeev.domain.repository.ChatsRepository
import ru.alexsergeev.domain.repository.MessageRepository
import ru.alexsergeev.domain.repository.UserProfileRepository

val dataModule = module {

    singleOf(::UserProfileRepositoryImpl) bind UserProfileRepository::class
    singleOf(::ChatsRepositoryImpl) bind ChatsRepository::class
    singleOf(::MessageRepositoryImpl) bind MessageRepository::class


}