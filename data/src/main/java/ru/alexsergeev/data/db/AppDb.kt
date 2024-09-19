package ru.alexsergeev.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.alexsergeev.data.dao.UserDao
import ru.alexsergeev.data.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class AppDb : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDb::class.java, "app.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()

        fun provideDao(db: AppDb) = db.userDao()
    }
}