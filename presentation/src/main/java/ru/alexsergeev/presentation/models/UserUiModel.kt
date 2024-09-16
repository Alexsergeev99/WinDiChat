package ru.alexsergeev.presentation.models

internal data class UserUiModel(
    val id: Int,
    val name: FullName,
    val phone: Phone,
    val avatar: String,
    val city: String = "",
)

internal data class FullName(
    val firstName: String,
    val secondName: String,
)

internal data class Phone(
    val countryCode: String,
    val basicNumber: String,
)