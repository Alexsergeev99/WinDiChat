package ru.alexsergeev.data.utils

import ru.alexsergeev.data.entity.UserEntity
import ru.alexsergeev.domain.models.FullName
import ru.alexsergeev.domain.models.Phone
import ru.alexsergeev.domain.models.UserDomainModel

internal class EntityUserToDomainUserMapper : Mapper<UserEntity, UserDomainModel> {
    override fun map(input: UserEntity): UserDomainModel = with(input) {
        UserDomainModel(
            id = id,
            FullName(
                firstName = input.name.firstName,
                secondName = input.name.secondName
            ),
            phone = Phone(
                countryCode = input.phone.countryCode,
                basicNumber = input.phone.basicNumber
            ),
            avatar = avatar,
            city = city,
            info = info,
            username = username
        )
    }
}