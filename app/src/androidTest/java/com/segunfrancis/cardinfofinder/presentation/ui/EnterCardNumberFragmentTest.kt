package com.segunfrancis.cardinfofinder.presentation.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.segunfrancis.cardinfofinder.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class EnterCardNumberFragmentTest {

    @get:Rule
    val rule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun enterCardNumberEditTextIsDisplayedTest() {

        onView(withId(R.id.card_1)).perform(click())

        onView(withId(R.id.card_number_editText)).check(matches(isDisplayed()))
    }
}