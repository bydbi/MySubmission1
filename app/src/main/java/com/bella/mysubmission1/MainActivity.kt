package com.bella.mysubmission1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bella.mysubmission1.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: mainViewModel
    private lateinit var adapter: userAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = userAdapter()
        adapter.notifyDataSetChanged()
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(mainViewModel::class.java)

        binding.apply {
            rvUsers.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUsers.setHasFixedSize(true)
            rvUsers.adapter = adapter

            search.setOnClickListener{
                userSearch()
            }
            edtText.setOnKeyListener {v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                    userSearch()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }

        viewModel.getUserSearch().observe(this, {
            if (it!= null){
                adapter.setList(it)
                loadingShow(false)
            }
        })
    }

    private fun userSearch(){
        binding.apply {
            val query = edtText.text.toString()
            if (query.isEmpty())
                return loadingShow(true)
            viewModel.setUserSearch(query)
        }
    }

    private fun loadingShow(state: Boolean){
        if (state){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }
}
