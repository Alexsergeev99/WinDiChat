package ru.alexsergeev.data.models

data class PhoneRequest(
    val phone: String
)

data class CodeRequest(
    val phone: String,
    val code: String
)

data class RegisterRequest(
    val phone: String,
    val name: String,
    val username: String,
)

data class RefreshCodeRequest(
    val refresh_token: String
)

data class UpdateUserRequest(
    val name: String,
    val username: String,
    val birthday: String,
    val city: String,
    val vk: String,
    val instagram: String,
    val status: String,
    val avatar: AvatarRequest,
)

data class AvatarRequest(
    val filename: String,
    val base_64: String,
)