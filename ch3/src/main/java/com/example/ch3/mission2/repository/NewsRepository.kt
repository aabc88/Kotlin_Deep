package com.example.ch3.mission2.repository

import com.example.ch3.mission2.AppApplication
import com.example.ch3.mission2.retrofit.RetrofitService
import com.example.ch3.mission2.model.PageList

import retrofit2.Callback

//누군가의 요청을 받아서 네트워킹을 통해 데이터를 구해오는 역할.
class NewsRepository {
    fun getNewsList(query: String, callback: Callback<PageList>) {
        val apiService = AppApplication.apiService()
        apiService.getNewsList(query, RetrofitService.API_KEY, 1, 10).enqueue(callback)
    }
}