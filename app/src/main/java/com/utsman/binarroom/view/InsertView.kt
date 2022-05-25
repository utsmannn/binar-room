package com.utsman.binarroom.view

import android.content.Context
import com.utsman.binarroom.model.User

interface InsertView {
    fun context(): Context
    fun onSaveDatabase()
}