package com.utsman.binarroom.features.second

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.utsman.binarroom.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity(), SecondView {
    private lateinit var binding: ActivitySecondBinding

    private val secondPresenter: SecondPresenter = SecondPresenterImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        secondPresenter.getUsernames()
    }

    override fun context(): Context {
        return this
    }

    override fun onResultData(usernames: String) {
        binding.tvUsers.text = usernames
    }
}