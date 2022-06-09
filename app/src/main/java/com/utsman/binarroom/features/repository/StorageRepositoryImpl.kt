package com.utsman.binarroom.features.repository

import android.content.Context
import com.utsman.binarroom.model.User
import com.utsman.binarroom.sources.UserDao
import com.utsman.binarroom.sources.UserDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class StorageRepositoryImpl(private val context: Context) : StorageRepository {

    private val userDatabase: UserDatabase? by lazy {
        UserDatabase.getInstance(context)
    }

    private val userDao: UserDao? by lazy {
        userDatabase?.userDao()
    }

    override fun getDatabase(result: (userData: List<User>) -> Unit) {
        GlobalScope.launch {
            val userData = userDao?.getAllUser().orEmpty()
            MainScope().launch {
                result.invoke(userData)
            }
        }
    }

    override fun getUsername(result: (username: String) -> Unit) {
        getDatabase { listUser ->
            val usernames = listUser.map { user ->
                user.name
            }.toString()
            result.invoke(usernames)
        }
    }

    override fun insertToDatabase(newUser: User, onSaved: () -> Unit) {
        GlobalScope.launch {
            val addUser = userDao?.addUser(newUser)
            MainScope().launch {
                onSaved.invoke()
            }
        }
    }
}