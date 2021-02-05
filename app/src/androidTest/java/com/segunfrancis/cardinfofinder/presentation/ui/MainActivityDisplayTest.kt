package com.segunfrancis.cardinfofinder.presentation.ui


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.segunfrancis.cardinfofinder.R
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityDisplayTest {

    @get:Rule
    var rule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivityDisplayTest() {
        val textView = onView(
            allOf(
                withId(R.id.textView2), withText("Select how you want to check your card info"),
                withParent(
                    allOf(
                        withId(R.id.main),
                        withParent(withId(R.id.fragmentContainerView))
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Select how you want to check your card info")))
    }
}
