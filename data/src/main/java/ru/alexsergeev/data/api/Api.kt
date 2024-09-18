package ru.alexsergeev.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import ru.alexsergeev.data.BuildConfig
import ru.alexsergeev.data.models.CodeRequest
import ru.alexsergeev.data.models.PhoneRequest
import ru.alexsergeev.data.models.RegisterRequest
import ru.alexsergeev.data.models.RegisterResponse
import ru.alexsergeev.data.models.SendCodeResponse
import ru.alexsergeev.data.models.VerifyCodeResponse
import java.util.concurrent.TimeUnit

internal const val BASE_URL = "https://plannerok.ru/"

internal interface ApiService {
    @POST("api/v1/users/send-auth-code/")
    suspend fun sendCode(@Body phoneRequest: PhoneRequest): Response<SendCodeResponse>

    @POST("api/v1/users/check-auth-code/")
    suspend fun verifyCode(@Body codeRequest: CodeRequest): Response<VerifyCodeResponse>

    @POST("api/v1/users/register/")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): Response<RegisterResponse>
}

internal fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(100, TimeUnit.SECONDS)
        .build()
} else OkHttpClient
    .Builder()
    .connectTimeout(100, TimeUnit.SECONDS)
    .build()

internal fun provideRetrofit(
    okHttpClient: OkHttpClient,
): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

internal fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)

