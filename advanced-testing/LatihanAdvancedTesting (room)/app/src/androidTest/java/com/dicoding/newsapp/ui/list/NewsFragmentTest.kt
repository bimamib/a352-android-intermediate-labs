package com.dicoding.newsapp.ui.list

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import org.junit.Assert.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dicoding.newsapp.data.remote.retrofit.ApiConfig
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.dicoding.newsapp.R

@RunWith(AndroidJUnit4::class)
class NewsFragmentTest {

    private val mockWebServer = MockWebServer()

    @Before
    fun setUp() {
        mockWebServer.start(8080)
        ApiConfig.BASE_URL = "http://127.0.0.1:8080/"
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getHeadlineNews_Success() {
        val bundle = Bundle()
        bundle.putString(NewsFragment.ARG_TAB, NewsFragment.TAB_NEWS)
        launchFragmentInContainer<NewsFragment>(bundle, R.style.Theme_News)
        onView(withId(R.id.rv_news))
            .check(matches(isDisplayed()))
        //check data is match
    }
}