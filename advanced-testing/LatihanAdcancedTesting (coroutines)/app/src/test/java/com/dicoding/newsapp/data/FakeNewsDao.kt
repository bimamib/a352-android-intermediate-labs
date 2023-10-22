package com.dicoding.newsapp.data

import androidx.lifecycle.LiveData
import com.dicoding.newsapp.data.local.entity.NewsEntity
import com.dicoding.newsapp.data.local.room.NewsDao

class FakeNewsDao : NewsDao {
    override fun getBookmarkedNews(): LiveData<List<NewsEntity>> {
        TODO("Not yet implemented")
    }

    override suspend fun saveNews(news: NewsEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNews(newsTitle: String) {
        TODO("Not yet implemented")
    }

    override fun isNewsBookmarked(title: String): LiveData<Boolean> {
        TODO("Not yet implemented")
    }
}