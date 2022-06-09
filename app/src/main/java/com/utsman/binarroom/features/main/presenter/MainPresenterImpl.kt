package com.utsman.binarroom.features.main.presenter

import com.utsman.binarroom.features.repository.StorageRepository
import com.utsman.binarroom.features.repository.StorageRepositoryImpl
import com.utsman.binarroom.view.MainView

// bertindak sebagai implementasi dari API
class MainPresenterImpl(private val view: MainView) : MainPresenter {
    private val storageRepository: StorageRepository by lazy {
        StorageRepositoryImpl(view.context())
    }

    override fun getDatabase() {
        storageRepository.getDatabase {
            view.onResultDatabase(it)
        }
    }
}