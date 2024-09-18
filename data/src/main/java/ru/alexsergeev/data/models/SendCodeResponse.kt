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

data class RegisterResponse(
    val refresh_token: String,
    val access_token: String,
    val user_id: Int,
)

data class GetUserResponse(
    val name: String,
    val username: String,
    val birthday: String,
    val city: String,
    val vk: String,
    val instagram: String,
    val status: String,
    val avatar: String,
    val id: Int,
    val last: String,
    val online: Boolean,
    val created: String,
    val phone: String,
    val completed_task: Int,
    val avatars: Avatars
)

data class Avatars(
    val avatar: String,
    val bigAvatar: String,
    val miniAvatar: String,
)