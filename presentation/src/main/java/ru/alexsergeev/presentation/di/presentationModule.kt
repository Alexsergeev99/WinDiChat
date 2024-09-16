package ru.alexsergeev.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.alexsergeev.presentation.utils.mappers.DomainUserToUiUserMapper
import ru.alexsergeev.presentation.utils.mappers.UiUserToDomainUserMapper
import ru.alexsergeev.presentation.viewmodel.InputPhoneNumberViewModel

val presentationModule = module {

    viewModelOf(::InputPhoneNumberViewModel)

    singleOf(::DomainUserToUiUserMapper)
    singleOf(::UiUserToDomainUserMapper)

}