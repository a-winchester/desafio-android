package com.picpay.desafio.android

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.ui.MainActivityViewModel
import com.picpay.desafio.android.ui.UserListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter
    private val mainViewModel: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)
        adapter = UserListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        mainViewModel.fetchUsers()

        mainViewModel.users.observe(this, Observer { users ->
            users?.let {
                adapter.users = users
                progressBar.visibility = View.GONE
            }
        })
    }

    override fun onResume() {
        super.onResume()

        progressBar = findViewById(R.id.user_list_progress_bar)
        progressBar.visibility = View.VISIBLE
    }
}
