package com.picpay.desafio.android

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.ui.MainActivityViewModel
import com.picpay.desafio.android.ui.UserListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

        mainViewModel.getUsersFromLocal()

        mainViewModel.userRoomList.observe(this) { users ->
            users?.let {
                adapter.users = users
                progressBar.visibility = View.GONE
            } ?: mainViewModel.getUsersFromRemote()
        }

        mainViewModel.userApiList.observe(this) { users ->
            if (mainViewModel.userRoomList.value.isNullOrEmpty()) {
                users?.let {
                    adapter.users = users
                    progressBar.visibility = View.GONE
                    CoroutineScope(Dispatchers.Main).launch {
                        mainViewModel.saveUsersLocally(users)
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        progressBar = findViewById(R.id.user_list_progress_bar)
        progressBar.visibility = View.VISIBLE
    }
}
