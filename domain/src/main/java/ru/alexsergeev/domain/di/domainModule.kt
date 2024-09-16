package ru.alexsergeev.domain.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.alexsergeev.domain.usecases.implementation.GetUserProfileUseCaseImpl
import ru.alexsergeev.domain.usecases.implementation.SetUserProfileUseCaseImpl
import ru.alexsergeev.domain.usecases.interfaces.GetUserProfileUseCase
import ru.alexsergeev.domain.usecases.interfaces.SetUserProfileUseCase

val domainModule = module {

    factoryOf(::GetUserProfileUseCaseImpl) bind GetUserProfileUseCase::class
    factoryOf(::SetUserProfileUseCaseImpl) bind SetUserProfileUseCase::class

}