package com.example.mvvmwithretrofit


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmwithretrofit.adapter.MyAdapter
import com.example.mvvmwithretrofit.api.RetrofitHelper
import com.example.mvvmwithretrofit.databinding.ActivityMainBinding
import com.example.mvvmwithretrofit.model.UsersItem
import com.example.mvvmwithretrofit.repository.GitRepository
import com.example.mvvmwithretrofit.viewModel.GitViewModel
import com.example.mvvmwithretrofit.viewModel.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {


    private var myViewModel : GitViewModel? = null
    private var gitList = mutableListOf<UsersItem>()
    private var adapter : MyAdapter? = null


    private var binding:ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val repository = GitRepository(RetrofitHelper.retroFitInstance)
        val viewModelFactory = ViewModelFactory(repository)
        myViewModel = ViewModelProvider(this,viewModelFactory)[GitViewModel::class.java]


        CoroutineScope(Dispatchers.IO).launch {
            myViewModel?.getData()
        }
        myViewModel?.gitObserver?.observe(this, Observer {
            it.forEach {data->
                gitList.add(data)
            }
            adapter?.notifyDataSetChanged()
        })
        adapter = MyAdapter(this,gitList)
        binding?.recyclerView?.layoutManager = LinearLayoutManager(this)
        binding?.recyclerView?.adapter = adapter
    }

}