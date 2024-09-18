package ru.alexsergeev.data.models

data class PhoneRequest(
    val phone: String
)

data class CodeRequest(
    val phone: String,
    val code: String
)