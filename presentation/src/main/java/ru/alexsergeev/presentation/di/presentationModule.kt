package ru.alexsergeev.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import ru.alexsergeev.presentation.utils.mappers.DomainChatToUiChatMapper
import ru.alexsergeev.presentation.utils.mappers.DomainMessageToUiMessageMapper
import ru.alexsergeev.presentation.utils.mappers.DomainUserToUiUserMapper
import ru.alexsergeev.presentation.utils.mappers.UiChatToDomainChatMapper
import ru.alexsergeev.presentation.utils.mappers.UiMessageToDomainMessageMapper
import ru.alexsergeev.presentation.utils.mappers.UiUserToDomainUserMapper
import ru.alexsergeev.presentation.viewmodel.CodeScreenViewModel
import ru.alexsergeev.presentation.viewmodel.InputPhoneNumberViewModel
import ru.alexsergeev.presentation.viewmodel.MainScreenViewModel
import ru.alexsergeev.presentation.viewmodel.MessagesListViewModel
import ru.alexsergeev.presentation.viewmodel.UserProfileViewModel


val presentationModule = module {

    viewModelOf(::InputPhoneNumberViewModel)
    viewModelOf(::CodeScreenViewModel)
    viewModelOf(::MainScreenViewModel)
    viewModelOf(::UserProfileViewModel)
    viewModelOf(::MessagesListViewModel)

    singleOf(::DomainUserToUiUserMapper)
    singleOf(::UiUserToDomainUserMapper)
    singleOf(::DomainChatToUiChatMapper)
    singleOf(::UiChatToDomainChatMapper)
    singleOf(::UiMessageToDomainMessageMapper)
    singleOf(::DomainMessageToUiMessageMapper)

}