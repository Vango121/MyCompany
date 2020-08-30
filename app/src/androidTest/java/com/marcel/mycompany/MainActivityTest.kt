package com.marcel.mycompany

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Test
    fun test_isActivityinView() {
        val activityScenario= ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.main)).check(matches(isDisplayed()))
        onView(withId(R.id.mainFragment)).check(matches(isDisplayed()))
    }

    @Test
    fun test_BottomNavVisability() {
        val activityScenario= ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.bottom_navigation)).check(matches(isDisplayed()))
    }

    @Test
    fun test_BottomNavigationWorkers() {
        val activityScenario= ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.workers)).perform(click())
        onView(withId(R.id.scrollView1)).check(matches(isDisplayed()))
        pressBack()
        onView(withId(R.id.mainFragment)).check(matches(isDisplayed()))
    }
    @Test
    fun test_BottomNavigationFinances() {
        val activityScenario= ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.finances)).perform(click())
        onView(withId(R.id.financesFragment)).check(matches(isDisplayed()))
    }


}