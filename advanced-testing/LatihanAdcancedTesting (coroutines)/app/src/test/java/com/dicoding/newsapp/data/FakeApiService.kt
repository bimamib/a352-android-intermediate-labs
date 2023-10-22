package com.dicoding.newsapp.data

import com.dicoding.newsapp.data.remote.response.NewsResponse
import com.dicoding.newsapp.data.remote.retrofit.ApiService

class FakeApiService : ApiService {
    override suspend fun getNews(apiKey: String): NewsResponse {
        TODO("Not yet implemented")
    }
}