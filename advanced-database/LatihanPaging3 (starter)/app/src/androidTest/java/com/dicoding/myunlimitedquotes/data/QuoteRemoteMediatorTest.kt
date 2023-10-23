package com.dicoding.myunlimitedquotes.data

import androidx.paging.ExperimentalPagingApi
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dicoding.myunlimitedquotes.database.QuoteDatabase
import com.dicoding.myunlimitedquotes.network.ApiService
import com.dicoding.myunlimitedquotes.network.QuoteResponseItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Assert.*
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExperimentalPagingApi
@RunWith(AndroidJUnit4::class)
class QuoteRemoteMediatorTest {

    private var mockApi: ApiService = FakeApiService()
    private var mockDb: QuoteDatabase = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(),
        QuoteDatabase::class.java
    ).allowMainThreadQueries().build()

    @After
    fun tearDown() {
        mockDb.clearAllTables()
    }
}

class FakeApiService : ApiService {

    override suspend fun getQuote(page: Int, size: Int): List<QuoteResponseItem> {
        val items: MutableList<QuoteResponseItem> = arrayListOf()
        for (i in 0..100) {
            val quote = QuoteResponseItem(
                i.toString(),
                "author + $i",
                "quote $i",
            )
            items.add(quote)
        }
        return items.subList((page - 1) * size, (page - 1) * size + size)
    }
}