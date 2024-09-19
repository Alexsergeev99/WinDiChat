package ru.alexsergeev.domain.models

data class UserDomainModel(
    val id: Int,
    val name: FullName,
    val phone: Phone,
    val avatar: String,
    val birthday: String = "",
    val city: String = "",
    val info: String = "",
    val username: String = ""
)

data class FullName(
    val firstName: String,
    val secondName: String,
)

data class Phone(
    val countryCode: String,
    val basicNumber: String,
)