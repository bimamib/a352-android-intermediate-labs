package com.dicoding.newsapp.ui.list

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.dicoding.newsapp.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

class HomeActivityTest {

    @get:Rule
    val activity = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }
}