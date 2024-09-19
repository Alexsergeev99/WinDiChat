package ru.alexsergeev.data.utils

import ru.alexsergeev.data.models.AvatarRequest
import ru.alexsergeev.data.models.UpdateUserRequest
import ru.alexsergeev.domain.models.UserDomainModel

internal class DomainUserToUpdateUserRequestMapper : Mapper<UserDomainModel, UpdateUserRequest> {
    override fun map(input: UserDomainModel): UpdateUserRequest = with(input) {
        UpdateUserRequest(
            name = "${input.name.firstName} ${input.name.secondName}",
            username = username,
            birthday = birthday,
            avatar = AvatarRequest(avatar, ""),
            city = city,
            status = info,
            vk = "",
            instagram = ""
        )
    }
}