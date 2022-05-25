package com.utsman.binarroom.features.insert.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.utsman.binarroom.model.User
import com.utsman.binarroom.sources.UserDao
import com.utsman.binarroom.sources.UserDatabase
import com.utsman.binarroom.databinding.ActivityInsertBinding
import com.utsman.binarroom.features.insert.presenter.InsertPresenter
import com.utsman.binarroom.features.insert.presenter.InsertPresenterImpl
import com.utsman.binarroom.view.InsertView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class InsertActivity : AppCompatActivity(), InsertView {
    private var _binding: ActivityInsertBinding? = null
    private lateinit var binding: ActivityInsertBinding

    private val insertPresenter: InsertPresenter = InsertPresenterImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityInsertBinding.inflate(layoutInflater)
        binding = requireNotNull(_binding)
        setContentView(binding.root)

        setupView()
    }

    override fun context(): Context {
        return this
    }

    private fun setupView() {
        binding.btnInsert.setOnClickListener {
            val name: String = binding.etName.text.toString()
            val age: Int = binding.etAge.text.toString().toIntOrNull() ?: 0

            val newUser = User(
                name = name,
                age = age
            )

            insertPresenter.saveToDatabase(newUser)
        }
    }

    override fun onSaveDatabase() {
        binding.etName.setText("")
        binding.etAge.setText("")
        onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}