package com.utsman.binarroom.features.insert.presenter

import android.widget.Toast
import com.utsman.binarroom.model.User
import com.utsman.binarroom.sources.UserDao
import com.utsman.binarroom.sources.UserDatabase
import com.utsman.binarroom.view.InsertView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class InsertPresenterImpl(private val view: InsertView) : InsertPresenter {

    private val userDatabase: UserDatabase? by lazy {
        UserDatabase.getInstance(view.context())
    }

    private val userDao: UserDao? by lazy {
        userDatabase?.userDao()
    }

    override fun saveToDatabase(newUser: User) {
        GlobalScope.launch {
            val addUser = userDao?.addUser(newUser)
            println("aaaa add user -> $addUser")

            MainScope().launch {
                view.onSaveDatabase()
            }
        }
    }
}