package com.utsman.binarroom.features.main.presenter

import com.utsman.binarroom.sources.UserDao
import com.utsman.binarroom.sources.UserDatabase
import com.utsman.binarroom.view.MainView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

// bertindak sebagai implementasi dari API
class MainPresenterImpl(private val view: MainView) : MainPresenter {

    private val userDatabase: UserDatabase? by lazy {
        UserDatabase.getInstance(view.context())
    }

    private val userDao: UserDao? by lazy {
        userDatabase?.userDao()
    }

    override fun getDatabase() {
        GlobalScope.launch {
            val userData = userDao?.getAllUser().orEmpty()
            MainScope().launch {
                view.onResultDatabase(userData)
            }
        }
    }
}