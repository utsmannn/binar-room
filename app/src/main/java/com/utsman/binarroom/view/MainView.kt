package com.utsman.binarroom.view

import android.content.Context
import com.utsman.binarroom.model.User

interface MainView {

    fun context(): Context
    fun onResultDatabase(users: List<User>)
}