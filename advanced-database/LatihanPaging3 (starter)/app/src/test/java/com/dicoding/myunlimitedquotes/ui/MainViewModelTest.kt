package com.dicoding.myunlimitedquotes.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dicoding.myunlimitedquotes.DataDummy
import com.dicoding.myunlimitedquotes.MainDispatcherRule
import com.dicoding.myunlimitedquotes.data.QuoteRepository
import com.dicoding.myunlimitedquotes.getOrAwaitValue
import com.dicoding.myunlimitedquotes.network.QuoteResponseItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRules = MainDispatcherRule()

    @Mock
    private lateinit var quoteRepository: QuoteRepository

    @Test
    fun `when Get Quote Should Not Null and Return Data`() = runTest {
        val dummyQuote = DataDummy.generateDummyQuoteResponse()
        val data: PagingData<QuoteResponseItem> = QuotePagingSource.snapshot(dummyQuote)
        val expectedQuote = MutableLiveData<PagingData<QuoteResponseItem>>()
        expectedQuote.value = data
        Mockito.`when`(quoteRepository.getQuote()).thenReturn(expectedQuote)

        val mainViewModel = MainViewModel(quoteRepository)
        val actualQuote: PagingData<QuoteResponseItem> = mainViewModel.quote.getOrAwaitValue()
    }
}

class QuotePagingSource : PagingSource<Int, LiveData<List<QuoteResponseItem>>>() {
    companion object {
        fun snapshot(items: List<QuoteResponseItem>): PagingData<QuoteResponseItem> {
            return PagingData.from(items)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, LiveData<List<QuoteResponseItem>>>): Int {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LiveData<List<QuoteResponseItem>>> {
        return LoadResult.Page(emptyList(), 0, 1)
    }
}