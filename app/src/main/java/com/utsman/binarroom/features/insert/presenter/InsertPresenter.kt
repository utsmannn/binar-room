package com.utsman.binarroom.features.insert.presenter

import com.utsman.binarroom.model.User

interface InsertPresenter {
    fun saveToDatabase(newUser: User)
}