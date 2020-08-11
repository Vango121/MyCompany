package com.marcel.mycompany

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.marcel.mycompany.screens.workers.WorkersFragment
import org.junit.Test


@RunWith(AndroidJUnit4::class)
class WorkersFragmentTest{
    @Test
    fun test_areImageViewsDisplayed() {
        val scenario = launchFragmentInContainer<WorkersFragment>()
        onView(withId(R.id.imageView2)).check(matches(isDisplayed())) // add workers
        onView(withId(R.id.imageView3)).check(matches(isDisplayed())) //remove workers
        onView(withId(R.id.imageView4)).check(matches(isDisplayed())) // edit contract
        onView(withId(R.id.imageView5)).check(matches(isDisplayed())) // wallet icon
    }

    @Test
    fun test_areTextsDisplayed() {
        val scenario = launchFragmentInContainer<WorkersFragment>()
        onView(withId(R.id.textView)).check(matches(withText(R.string.manage_workers))) // manage workers text
        onView(withId(R.id.textViewEdit)).check(matches(withText(R.string.edit_contracts_workers))) // edit_contracts_workers text
        onView(withId(R.id.textViewHours)).check(matches(withText(R.string.working_hours))) // working hours string
        onView(withId(R.id.textViewPay)).check(matches(withText(R.string.sum_amount_workers))) // sum of payment
    }

    @Test
    fun test_addworkersDialog() {
        val scenario = launchFragmentInContainer<WorkersFragment>()
        onView(withId(R.id.imageView2)).perform(click())
        onView(withId(R.id.dialogBar)).check(matches(withText(R.string.add_worker)))
        onView(withId(android.R.id.button2)).perform(click())
        onView(withText(R.string.add_worker)).check(doesNotExist())

    }
}