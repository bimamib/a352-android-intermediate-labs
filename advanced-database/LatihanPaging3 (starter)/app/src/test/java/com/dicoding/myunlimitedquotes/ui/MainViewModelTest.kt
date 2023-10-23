package com.dicoding.myunlimitedquotes.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dicoding.myunlimitedquotes.MainDispatcherRule
import com.dicoding.myunlimitedquotes.data.QuoteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
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
}