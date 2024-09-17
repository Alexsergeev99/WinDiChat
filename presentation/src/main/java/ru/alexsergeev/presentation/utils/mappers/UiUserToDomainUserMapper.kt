package ru.alexsergeev.presentation.utils.mappers

import ru.alexsergeev.domain.models.FullName
import ru.alexsergeev.domain.models.Phone
import ru.alexsergeev.domain.models.UserDomainModel
import ru.alexsergeev.presentation.models.UserUiModel

internal class UiUserToDomainUserMapper : Mapper<UserUiModel, UserDomainModel> {
    override fun map(input: UserUiModel): UserDomainModel = with(input) {
        UserDomainModel(
            id = id,
            FullName(
                firstName = input.name.firstName,
                secondName = input.name.secondName
            ),
            phone = Phone(
                input.phone.countryCode,
                input.phone.basicNumber
            ),
            avatar = avatar,
            city = city,
            info = info,
            username = username
        )
    }
}