package ru.alexsergeev.data.utils

import ru.alexsergeev.data.models.GetUserResponse
import ru.alexsergeev.domain.models.FullName
import ru.alexsergeev.domain.models.Phone
import ru.alexsergeev.domain.models.UserDomainModel

internal interface Mapper<in I, out O> {
    fun map(input: I): O
}

internal class DataUserToDomainUserMapper : Mapper<GetUserResponse, UserDomainModel> {
    override fun map(input: GetUserResponse): UserDomainModel = with(input) {
        val name = input.name.split(" ")
        UserDomainModel(
            id = id,
            FullName(
                firstName = name[0],
                secondName = name[1]
            ),
            phone = Phone(
                countryCode = "${input.phone[0]}${input.phone[1]}",
                basicNumber = "${input.phone[2]}${input.phone[3]}${input.phone[4]}${input.phone[5]}${input.phone[6]}${input.phone[7]}${input.phone[8]}${input.phone[9]}"
            ),
            avatar = avatar,
            city = city,
            info = status,
            username = username
        )
    }
}