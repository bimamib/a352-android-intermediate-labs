package com.dicoding.myunlimitedquotes.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dicoding.myunlimitedquotes.network.ApiService
import com.dicoding.myunlimitedquotes.network.QuoteResponseItem

class QuotePagingSource(private val apiService: ApiService) : PagingSource<Int, QuoteResponseItem>() {
    override fun getRefreshKey(state: PagingState<Int, QuoteResponseItem>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, QuoteResponseItem> {
        TODO("Not yet implemented")
    }
}