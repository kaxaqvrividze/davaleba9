package com.example.davaleba9android.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.davaleba9android.R
import com.example.davaleba9android.adapter.RecyclerViewAdapter
import com.example.davaleba9android.model.DataUser
import com.example.davaleba9android.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var viewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViewModel()
        initMainViewModel()

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getAllRepositoryList().observe(this) {
            recyclerViewAdapter.listDataUser = it
        }

        val buttonRefreshData = findViewById<FloatingActionButton>(R.id.button_refresh_data)
        buttonRefreshData.setOnClickListener {
            Log.d("logging", "refresh Data from API")
            initViewModel()
            initMainViewModel()
        }

    }

    private fun initViewModel() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)

            val decoration =
                DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerViewAdapter = RecyclerViewAdapter()
            adapter = recyclerViewAdapter
            Log.d("logging", "prepare ViewModel")
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initMainViewModel() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getAllRepositoryList().observe(this, Observer<List<DataUser>> {
            recyclerViewAdapter.setListData(it)
            recyclerViewAdapter.notifyDataSetChanged()
        })
        viewModel.makeApiCall()
        Log.d("logging", "make Main ViewModel")

        // setup click listener for edit
        recyclerViewAdapter.onDataUserClickListener = {
            // log (element id) to click
            Log.d("logging", "click by " + it.id.toString())
            val firstname = it.first_name
            val lastname = it.last_name
            val email = it.email
            val avatar = it.avatar
            val intent = EditUserActivity
                .newIntentEditDataUser(this, it.id, firstname, lastname, email, avatar)
            startActivity(intent)
        }
    }
}