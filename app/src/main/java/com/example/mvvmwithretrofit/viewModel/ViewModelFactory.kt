package com.example.mvvmwithretrofit.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmwithretrofit.repository.GitRepository

class ViewModelFactory(private val moviesRepository: GitRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GitViewModel::class.java)){
            return GitViewModel(moviesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}