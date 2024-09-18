package ru.alexsergeev.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import ru.alexsergeev.data.BuildConfig
import ru.alexsergeev.data.models.CodeRequest
import ru.alexsergeev.data.models.GetUserResponse
import ru.alexsergeev.data.models.PhoneRequest
import ru.alexsergeev.data.models.RefreshCodeRequest
import ru.alexsergeev.data.models.RegisterRequest
import ru.alexsergeev.data.models.RegisterResponse
import ru.alexsergeev.data.models.SendCodeResponse
import ru.alexsergeev.data.models.VerifyCodeResponse
import java.util.concurrent.TimeUnit

internal const val BASE_URL = "https://plannerok.ru/api/v1/users/"

internal interface ApiService {
    @POST("send-auth-code/")
    suspend fun sendCode(@Body phoneRequest: PhoneRequest): Response<SendCodeResponse>

    @POST("check-auth-code/")
    suspend fun verifyCode(@Body codeRequest: CodeRequest): Response<VerifyCodeResponse>

    @POST("register/")
    suspend fun registerUser(@Body registerRequest: RegisterRequest): Response<RegisterResponse>

    @POST("refresh-token/")
    suspend fun refreshToken(@Body refreshTokenRequest: RefreshCodeRequest): Response<RegisterResponse>

    @GET("me/")
    suspend fun getUser(@Header("Authorization") access_token: String): Response<GetUserResponse>
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

