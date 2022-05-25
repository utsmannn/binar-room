package com.utsman.binarroom.sources

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.utsman.binarroom.model.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var _instance: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase? {
            if (_instance == null) {
                synchronized(UserDatabase::class) {
                    _instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user.db"
                    ).build()
                }
            }

            return _instance
        }

        fun destroyDatabase() {
            _instance = null
        }
    }
}