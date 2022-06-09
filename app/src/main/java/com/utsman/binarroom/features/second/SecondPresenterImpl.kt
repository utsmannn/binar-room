package com.utsman.binarroom.features.second

import com.utsman.binarroom.features.repository.StorageRepository
import com.utsman.binarroom.features.repository.StorageRepositoryImpl

class SecondPresenterImpl(private val view: SecondView) : SecondPresenter {
    private val storageRepository: StorageRepository by lazy {
        StorageRepositoryImpl(view.context())
    }

    override fun getUsernames() {
        storageRepository.getUsername {
            view.onResultData(it)
        }
    }
}