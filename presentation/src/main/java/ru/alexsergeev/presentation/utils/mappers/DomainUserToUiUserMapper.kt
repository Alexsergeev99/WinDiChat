package ru.alexsergeev.presentation.utils.mappers

import ru.alexsergeev.domain.models.ChatDomainModel
import ru.alexsergeev.domain.models.UserDomainModel
import ru.alexsergeev.presentation.models.ChatUiModel
import ru.alexsergeev.presentation.models.FullName
import ru.alexsergeev.presentation.models.Phone
import ru.alexsergeev.presentation.models.UserUiModel


internal class DomainUserToUiUserMapper : Mapper<UserDomainModel, UserUiModel> {
    override fun map(input: UserDomainModel): UserUiModel = with(input) {
        UserUiModel(
            id,
            name = FullName(firstName = input.name.firstName, secondName = input.name.secondName),
            phone = Phone(input.phone.countryCode, input.phone.basicNumber),
            avatar = avatar,
            city = city,
            info = info,
            username = username
        )
    }
}