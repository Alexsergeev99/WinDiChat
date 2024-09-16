package ru.alexsergeev.domain.models

data class UserDomainModel(
    val id: Int,
    val name: FullName,
    val phone: Phone,
    val avatar: String,
    val city: String = "",
)

data class FullName(
    val firstName: String,
    val secondName: String,
)

data class Phone(
    val countryCode: String,
    val basicNumber: String,
)