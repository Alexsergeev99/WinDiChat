package ru.alexsergeev.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.alexsergeev.data.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM UserEntity WHERE basicNumber = :basicNumber")
    fun getUserByPhone(basicNumber: String): Flow<UserEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: UserEntity)

    @Query("DELETE FROM USERENTITY")
    suspend fun clear()

}