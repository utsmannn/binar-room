package com.utsman.binarroom.features.repository

import com.utsman.binarroom.model.User

interface StorageRepository {
    fun getDatabase(result: (userData: List<User>) -> Unit)
    fun getUsername(result: (username: String) -> Unit)
    fun insertToDatabase(newUser: User, onSaved: () -> Unit)
}