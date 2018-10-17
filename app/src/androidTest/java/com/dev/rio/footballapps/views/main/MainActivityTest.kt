package com.dev.rio.footballapps.views.main


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import com.dev.rio.footballapps.R.id.*
import kotlinx.android.synthetic.main.fragment_matches.*
import org.junit.Rule
import org.junit.Test
import java.lang.Thread.sleep

class MainActivityTest{
    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    private val timeRequest = 3000L

    @Test
    fun testAppBehaviour() {
        //Main Match Test
        onView(withId(frame_mainFrame))
                .check(matches(isDisplayed()))
        onView(withId(bottomNavigationMain))
                .check(matches(isDisplayed()))

        //Matches Test
        onView(withId(item_matches))
                .perform(click())

        //Next Match Test
        onView(withText("NEXT"))
                .perform(click())
        testRecyclerViewBehaviour(rv_next_matches,12)
        testDetailViewBehaviour(rv_next_matches, "Add to favorite")

        //Last Match Test
        onView(withText("LAST"))
                .perform(click())
        testRecyclerViewBehaviour(rv_last_matches,13)
        testDetailViewBehaviour(rv_last_matches, "Add to favorite")

        //Favorite Match Test
        onView(withId(item_favorites))
                .perform(click())
        testRecyclerViewBehaviour(rv_matches_favorites,0)
        testDetailViewBehaviour(rv_matches_favorites, "Removed from favorite")
        testRecyclerViewBehaviour(rv_matches_favorites,0)
        testDetailViewBehaviour(rv_matches_favorites, "Removed from favorite")
    }

    private fun testRecyclerViewBehaviour(id: Int, position: Int) {
        sleep(timeRequest)
        onView(withId(id))
                .check(matches(isDisplayed()))
        onView(withId(id))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))
        onView(withId(id))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position, click()))
    }

    private fun testDetailViewBehaviour(idRecyclerView: Int, text: String) {
        onView(withId(sv_detail_match))
                .check(matches(isDisplayed()))
        sleep(timeRequest)
        onView(withId(iv_homeLogo))
                .check(matches(isDisplayed()))
        onView(withId(iv_awayLogo))
                .check(matches(isDisplayed()))
        onView(withId(add_to_favorite))
                .check(matches(isDisplayed()))
        onView(withId(add_to_favorite))
                .perform(click())
        onView(withText(text))
                .check(matches(isDisplayed()))
        pressBack()
        onView(withId(idRecyclerView))
                .perform(swipeDown())
        onView(withId(idRecyclerView))
                .check(matches(isDisplayed()))
    }
}