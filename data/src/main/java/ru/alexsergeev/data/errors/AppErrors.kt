package ru.alexsergeev.data.errors

sealed class AppErrors(var code: String) : RuntimeException()
class ApiError(val status: Int, code: String) : AppErrors(code)
data object NetworkError : AppErrors("error_network")
data object UnknownError : AppErrors("error_unknown")