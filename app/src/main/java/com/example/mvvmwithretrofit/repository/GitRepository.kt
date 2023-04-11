package com.example.mvvmwithretrofit.repository

import com.example.mvvmwithretrofit.api.ApiInterface

class GitRepository(private val apiServiceImp: ApiInterface) {

     suspend fun getDataList() = apiServiceImp.getData()
}