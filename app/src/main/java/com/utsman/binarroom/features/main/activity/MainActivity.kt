package com.utsman.binarroom.features.main.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.utsman.binarroom.model.User
import com.utsman.binarroom.sources.UserDatabase
import com.utsman.binarroom.databinding.ActivityMainBinding
import com.utsman.binarroom.features.insert.activity.InsertActivity
import com.utsman.binarroom.features.adapter.UserAdapter
import com.utsman.binarroom.features.main.presenter.MainPresenter
import com.utsman.binarroom.features.main.presenter.MainPresenterImpl
import com.utsman.binarroom.view.MainView

class MainActivity : AppCompatActivity(), MainView {

    private val userAdapter = UserAdapter()
    private val mainPresenter: MainPresenter = MainPresenterImpl(this)

    private var _binding: ActivityMainBinding? = null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        binding = requireNotNull(_binding)

        setContentView(binding.root)

        setupView()
        mainPresenter.getDatabase()
    }

    override fun context(): Context {
        return this
    }

    private fun setupView() {
        val linearLayout = LinearLayoutManager(this)
        binding.mainRv.layoutManager = linearLayout
        binding.mainRv.adapter = userAdapter

        binding.mainFab.setOnClickListener {
            val intent = Intent(this, InsertActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResultDatabase(users: List<User>) {
        userAdapter.addList(users)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        UserDatabase.destroyDatabase()
    }

    override fun onResume() {
        super.onResume()
        mainPresenter.getDatabase()
    }
}