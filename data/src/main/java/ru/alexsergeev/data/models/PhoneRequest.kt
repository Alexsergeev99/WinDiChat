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