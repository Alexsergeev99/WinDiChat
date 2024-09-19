package ru.alexsergeev.presentation.models

internal data class UserUiModel(
    val id: Int,
    val name: FullName,
    val phone: Phone,
    val avatar: String,
    val birthday: String = "",
    val city: String = "",
    val info: String = "",
    val username: String = ""
)

internal data class FullName(
    val firstName: String,
    val secondName: String,
)

internal data class Phone(
    val countryCode: String,
    val basicNumber: String,
)