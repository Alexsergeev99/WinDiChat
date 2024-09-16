package ru.alexsergeev.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import ru.alexsergeev.domain.models.FullName
import ru.alexsergeev.domain.models.Phone
import ru.alexsergeev.domain.models.UserDomainModel
import ru.alexsergeev.domain.repository.UserProfileRepository

internal class UserProfileRepositoryImpl : UserProfileRepository {

    private val userDataMutable = MutableStateFlow(
        UserDomainModel(
            1,
            FullName("", ""),
            phone = Phone("+7", ""),
            "https://www.1zoom.me/big2/62/199578-yana.jpg",
        )
    )

    override fun getUserData(): Flow<UserDomainModel> = flow {
        val person = userDataMutable.value
        emit(person)
    }

    override suspend fun setUserData(user: UserDomainModel) {
        userDataMutable.update { user }
    }

    override fun verifyCode(code: Int): Flow<Boolean> = flow {
        val trueCode = code == 133337
        emit(trueCode)
    }
}