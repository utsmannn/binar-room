package com.utsman.binarroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.utsman.binarroom.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val userDummy = listOf(
        User(name = "Fajri", age = 20),
        User(name = "Wahyu", age = 23),
        User(name = "Nindi", age = 19)
    )

    private val userAdapter = UserAdapter()

    private var _binding: ActivityMainBinding? = null
    private lateinit var binding: ActivityMainBinding

    private val userDatabase: UserDatabase? by lazy {
        UserDatabase.getInstance(this)
    }

    private val userDao: UserDao? by lazy {
        userDatabase?.userDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        binding = requireNotNull(_binding)

        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {
        val linearLayout = LinearLayoutManager(this)
        binding.mainRv.layoutManager = linearLayout
        binding.mainRv.adapter = userAdapter

        binding.mainFab.setOnClickListener {
            val intent = Intent(this, InsertActivity::class.java)
            startActivity(intent)
        }

        getUserDatabase()
    }

    private fun getUserDatabase() {
        GlobalScope.launch {
            val userData = userDao?.getAllUser().orEmpty()
            runOnUiThread {
                userAdapter.addList(userData)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        UserDatabase.destroyDatabase()
    }

    override fun onResume() {
        super.onResume()
        getUserDatabase()
    }
}