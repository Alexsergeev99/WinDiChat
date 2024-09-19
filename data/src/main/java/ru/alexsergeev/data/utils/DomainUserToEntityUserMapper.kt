package ru.alexsergeev.data.utils

import ru.alexsergeev.data.entity.FullName
import ru.alexsergeev.data.entity.Phone
import ru.alexsergeev.data.entity.UserEntity
import ru.alexsergeev.domain.models.UserDomainModel

internal class DomainUserToEntityUserMapper : Mapper<UserDomainModel, UserEntity> {
    override fun map(input: UserDomainModel): UserEntity = with(input) {
        UserEntity(
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