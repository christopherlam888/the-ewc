package com.example.the_ewc

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class GlossaryInstrumentedTest {
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun check_first_term() {
        onView(withId(R.id.glossary_button)).perform(click())
        onView(withId(R.id.glossary_recycler_view))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
            .check(matches(hasDescendant(withText(glossarySelected[0].term))))
    }

    @Test
    fun check_random_term() {
        onView(withId(R.id.glossary_button)).perform(click())
        val idx = (0 until glossarySelected.size).random()
        onView(withId(R.id.glossary_recycler_view))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(idx))
            .check(matches(hasDescendant(withText(glossarySelected[idx].term))))
    }

    @Test
    fun check_random_term_details() {
        onView(withId(R.id.glossary_button)).perform(click())
        val idx = (0 until glossarySelected.size).random()
        onView(withId(R.id.glossary_recycler_view))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    idx,
                    click()
                )
            )
        onView(withId(R.id.term_details_term)).check(matches(withText(glossarySelected[idx].term)))
    }

    @Test
    fun check_brands_filter() {
        onView(withId(R.id.glossary_button)).perform(click())
        onView(withId(R.id.buttonBrands)).perform(click())
        val idx = (0 until glossarySelected.size).random()
        onView(withId(R.id.glossary_recycler_view))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    idx,
                    click()
                )
            )
        onView(withId(R.id.term_details_category)).check(matches(withText(glossarySelected[idx].category)))
    }

    @Test
    fun check_general_filter() {
        onView(withId(R.id.glossary_button)).perform(click())
        onView(withId(R.id.buttonGeneral)).perform(click())
        val idx = (0 until glossarySelected.size).random()
        onView(withId(R.id.glossary_recycler_view))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    idx,
                    click()
                )
            )
        onView(withId(R.id.term_details_category)).check(matches(withText(glossarySelected[idx].category)))
    }

    @Test
    fun check_all_filter() {
        onView(withId(R.id.glossary_button)).perform(click())
        onView(withId(R.id.buttonBrands)).perform(click())
        onView(withId(R.id.buttonAll)).perform(click())
        try {
            onView(withId(R.id.glossary_recycler_view))
                .perform(
                    RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                        glossarySelected.size - 1
                    )
                )
                .check(matches(isDisplayed()))
        } catch (_: NoMatchingViewException) {
        }
    }
}
