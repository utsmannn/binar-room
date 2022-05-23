package com.utsman.binarroom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.utsman.binarroom.databinding.ActivityInsertBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class InsertActivity : AppCompatActivity() {
    private var _binding: ActivityInsertBinding? = null
    private lateinit var binding: ActivityInsertBinding

    private val userDatabase: UserDatabase? by lazy {
        UserDatabase.getInstance(this)
    }

    private val userDao: UserDao? by lazy {
        userDatabase?.userDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityInsertBinding.inflate(layoutInflater)
        binding = requireNotNull(_binding)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        binding.btnInsert.setOnClickListener {
            val name: String = binding.etName.text.toString()
            val age: Int = binding.etAge.text.toString().toIntOrNull() ?: 0

            val newUser = User(
                name = name,
                age = age
            )

            saveToDatabase(newUser)
        }
    }

    private fun saveToDatabase(user: User) {
        GlobalScope.launch {
            val addUser = userDao?.addUser(user)
            println("aaaa add user -> $addUser")

            runOnUiThread {
                binding.etName.setText("")
                binding.etAge.setText("")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}