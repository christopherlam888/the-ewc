package com.example.the_ewc

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
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
class VideosInstrumentedTest {
  @get:Rule() val activity = ActivityScenarioRule(MainActivity::class.java)
  @Test
  fun check_video() {
    onView(withId(R.id.videos_button)).perform(click())
    onView(withId(R.id.videos_recycler_view))
      .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
      .check(matches(hasDescendant(withText(videos[0].title))))
  }
  @Test
  fun check_random_video() {
    onView(withId(R.id.videos_button)).perform(click())
    val idx = (0 until videos.size).random()
    onView(withId(R.id.videos_recycler_view))
      .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(idx))
      .check(matches(hasDescendant(withText(videos[idx].title))))
  }
  @Test
  fun check_video_reversed() {
    onView(withId(R.id.videos_button)).perform(click())
    onView(withId(R.id.buttonReverse)).perform(click())
    onView(withId(R.id.videos_recycler_view))
      .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
      .check(matches(hasDescendant(withText(videos[0].title))))
  }
  @Test
  fun check_random_video_reversed() {
    onView(withId(R.id.videos_button)).perform(click())
    onView(withId(R.id.buttonReverse)).perform(click())
    val idx = (0 until videos.size).random()
    onView(withId(R.id.videos_recycler_view))
      .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(idx))
      .check(matches(hasDescendant(withText(videos[idx].title))))
  }
  @Test
  fun check_video_reversed_forward() {
    onView(withId(R.id.videos_button)).perform(click())
    onView(withId(R.id.buttonReverse)).perform(click())
    onView(withId(R.id.buttonForward)).perform(click())
    onView(withId(R.id.videos_recycler_view))
      .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
      .check(matches(hasDescendant(withText(videos[0].title))))
  }
}
