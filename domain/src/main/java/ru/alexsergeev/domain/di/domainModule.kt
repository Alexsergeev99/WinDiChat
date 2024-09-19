package ru.alexsergeev.domain.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.alexsergeev.domain.usecases.implementation.GetAllChatsUseCaseImpl
import ru.alexsergeev.domain.usecases.implementation.GetAllMessagesUseCaseImpl
import ru.alexsergeev.domain.usecases.implementation.GetAllUsersUseCaseImpl
import ru.alexsergeev.domain.usecases.implementation.GetUseCaseUserProfileWithoutApiImpl
import ru.alexsergeev.domain.usecases.implementation.GetUserByIdUseCaseImpl
import ru.alexsergeev.domain.usecases.implementation.GetUserProfileUseCaseImpl
import ru.alexsergeev.domain.usecases.implementation.RegisterUserUseCaseImpl
import ru.alexsergeev.domain.usecases.implementation.SendCodeUseCaseImpl
import ru.alexsergeev.domain.usecases.implementation.SendMessageUseCaseImpl
import ru.alexsergeev.domain.usecases.implementation.SetUserProfileUseCaseImpl
import ru.alexsergeev.domain.usecases.implementation.UpdateUserProfileUseCaseImpl
import ru.alexsergeev.domain.usecases.implementation.ValidateCodeUseCaseImpl
import ru.alexsergeev.domain.usecases.interfaces.GetAllChatsUseCase
import ru.alexsergeev.domain.usecases.interfaces.GetAllMessagesUseCase
import ru.alexsergeev.domain.usecases.interfaces.GetAllUsersUseCase
import ru.alexsergeev.domain.usecases.interfaces.GetUserByIdUseCase
import ru.alexsergeev.domain.usecases.interfaces.GetUserProfileUseCase
import ru.alexsergeev.domain.usecases.interfaces.GetUserProfileWithoutApiUseCase
import ru.alexsergeev.domain.usecases.interfaces.RegisterUserUseCase
import ru.alexsergeev.domain.usecases.interfaces.SendCodeUseCase
import ru.alexsergeev.domain.usecases.interfaces.SendMessageUseCase
import ru.alexsergeev.domain.usecases.interfaces.SetUserProfileUseCase
import ru.alexsergeev.domain.usecases.interfaces.UpdateUserProfileUseCase
import ru.alexsergeev.domain.usecases.interfaces.ValidateCodeUseCase

val domainModule = module {

    factoryOf(::GetUserProfileUseCaseImpl) bind GetUserProfileUseCase::class
    factoryOf(::SetUserProfileUseCaseImpl) bind SetUserProfileUseCase::class
    factoryOf(::UpdateUserProfileUseCaseImpl) bind UpdateUserProfileUseCase::class
    factoryOf(::ValidateCodeUseCaseImpl) bind ValidateCodeUseCase::class
    factoryOf(::GetUserByIdUseCaseImpl) bind GetUserByIdUseCase::class
    factoryOf(::GetAllUsersUseCaseImpl) bind GetAllUsersUseCase::class
    factoryOf(::GetAllChatsUseCaseImpl) bind GetAllChatsUseCase::class
    factoryOf(::GetAllMessagesUseCaseImpl) bind GetAllMessagesUseCase::class
    factoryOf(::SendMessageUseCaseImpl) bind SendMessageUseCase::class
    factoryOf(::SendCodeUseCaseImpl) bind SendCodeUseCase::class
    factoryOf(::RegisterUserUseCaseImpl) bind RegisterUserUseCase::class
    factoryOf(::GetUseCaseUserProfileWithoutApiImpl) bind GetUserProfileWithoutApiUseCase::class

}