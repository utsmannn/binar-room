package com.utsman.binarroom.features.second

import android.content.Context

interface SecondView {
    fun context(): Context
    fun onResultData(usernames: String)
}