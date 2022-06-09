package com.utsman.binarroom.features.insert.presenter

import com.utsman.binarroom.features.repository.StorageRepository
import com.utsman.binarroom.features.repository.StorageRepositoryImpl
import com.utsman.binarroom.model.User
import com.utsman.binarroom.view.InsertView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class InsertPresenterImpl(private val view: InsertView) : InsertPresenter {
    private val storageRepository: StorageRepository by lazy {
        StorageRepositoryImpl(view.context())
    }

    override fun saveToDatabase(newUser: User) {
        GlobalScope.launch {
            storageRepository.insertToDatabase(newUser) {
                view.onSaveDatabase()
            }
        }
    }
}