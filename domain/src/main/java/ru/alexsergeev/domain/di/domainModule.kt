package ru.alexsergeev.domain.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.alexsergeev.domain.usecases.implementation.GetAllChatsUseCaseImpl
import ru.alexsergeev.domain.usecases.implementation.GetAllMessagesUseCaseImpl
import ru.alexsergeev.domain.usecases.implementation.GetAllUsersUseCaseImpl
import ru.alexsergeev.domain.usecases.implementation.GetUserByIdUseCaseImpl
import ru.alexsergeev.domain.usecases.implementation.GetUserProfileUseCaseImpl
import ru.alexsergeev.domain.usecases.implementation.SetUserProfileUseCaseImpl
import ru.alexsergeev.domain.usecases.implementation.ValidateCodeUseCaseImpl
import ru.alexsergeev.domain.usecases.interfaces.GetAllChatsUseCase
import ru.alexsergeev.domain.usecases.interfaces.GetAllMessagesUseCase
import ru.alexsergeev.domain.usecases.interfaces.GetAllUsersUseCase
import ru.alexsergeev.domain.usecases.interfaces.GetUserByIdUseCase
import ru.alexsergeev.domain.usecases.interfaces.GetUserProfileUseCase
import ru.alexsergeev.domain.usecases.interfaces.SetUserProfileUseCase
import ru.alexsergeev.domain.usecases.interfaces.ValidateCodeUseCase

val domainModule = module {

    factoryOf(::GetUserProfileUseCaseImpl) bind GetUserProfileUseCase::class
    factoryOf(::SetUserProfileUseCaseImpl) bind SetUserProfileUseCase::class
    factoryOf(::ValidateCodeUseCaseImpl) bind ValidateCodeUseCase::class
    factoryOf(::GetUserByIdUseCaseImpl) bind GetUserByIdUseCase::class
    factoryOf(::GetAllUsersUseCaseImpl) bind GetAllUsersUseCase::class
    factoryOf(::GetAllChatsUseCaseImpl) bind GetAllChatsUseCase::class
    factoryOf(::GetAllMessagesUseCaseImpl) bind GetAllMessagesUseCase::class

}