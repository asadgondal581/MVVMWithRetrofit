package com.example.mvvmwithretrofit.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmwithretrofit.model.UsersItem
import com.example.mvvmwithretrofit.repository.GitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GitViewModel(private val gitRepository: GitRepository) : ViewModel() {
    val gitObserver = MutableLiveData<List<UsersItem>>()
    suspend fun getData() {
        val response = gitRepository.getDataList()
        if (response.isSuccessful) {
            withContext(Dispatchers.Main) {
                gitObserver.value = response.body()
            }
        } else {
            Log.d("checkError: ", response.message())
        }
    }
}