package com.utsman.binarroom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.utsman.binarroom.databinding.ActivityUpdateBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpdateActivity : AppCompatActivity() {

    private var _binding: ActivityUpdateBinding? = null
    private lateinit var binding: ActivityUpdateBinding

    private val userDatabase: UserDatabase? by lazy {
        UserDatabase.getInstance(this)
    }

    private val userDao: UserDao? by lazy {
        userDatabase?.userDao()
    }

    private val id: Int by lazy {
        intent.getIntExtra("user_id", 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUpdateBinding.inflate(layoutInflater)
        binding = requireNotNull(_binding)

        setContentView(binding.root)

        setupView()
        fetchUser()
    }

    private fun setupView() {
        binding.btnUpdate.setOnClickListener {
            val name: String = binding.etName.text.toString()
            val age: Int = binding.etAge.text.toString().toIntOrNull() ?: 0

            val updatedUser = User(
                id = id,
                name = name,
                age = age
            )

            updateUser(updatedUser)
        }
    }

    private fun fetchUser() {
        GlobalScope.launch {
            val user = userDao?.getUserById(id)

            runOnUiThread {
                if (user != null) {
                    binding.etName.setText(user.name)
                    binding.etAge.setText(user.age.toString())
                }
            }
        }
    }

    private fun updateUser(user: User) {
        GlobalScope.launch {
            val addUser = userDao?.updateUser(user)
            println("aaaa update user -> $addUser")

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