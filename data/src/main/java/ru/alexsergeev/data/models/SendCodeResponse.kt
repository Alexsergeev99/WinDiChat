package ru.alexsergeev.data.models

data class SendCodeResponse(
    val is_success: Boolean
)

data class VerifyCodeResponse(
    val refresh_token: String,
    val access_token: String,
    val user_id: Int,
    val is_user_exists: Boolean
)