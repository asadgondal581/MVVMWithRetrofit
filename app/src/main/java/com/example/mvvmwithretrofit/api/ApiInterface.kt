package com.example.mvvmwithretrofit.api


import com.example.mvvmwithretrofit.model.UsersItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("users")
    suspend fun getData(): Response<List<UsersItem>>

}