package ru.edu.qamid.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import ru.edu.qamid.R

/**
 * Page Object экрана "Our Mission" (цитаты).
 */
class OurMissionPage {

    fun checkTitleIsDisplayed(): OurMissionPage {
        onView(withText("Love is all")).check(matches(isDisplayed()))
        return this
    }

    /**
     * Проверяем, что список цитат (RecyclerView) отображается.
     * RecyclerView на экране один, поэтому Ambiguous не будет.
     */
    fun checkQuoteListIsDisplayed(): OurMissionPage {
        onView(withId(R.id.our_mission_item_list_recycler_view))
            .check(matches(isDisplayed()))
        return this
    }

    fun clickFirstQuote(): OurMissionPage {
        // Клик по первой карточке через Espresso RecyclerView actions
        androidx.test.espresso.contrib.RecyclerViewActions
            .actionOnItemAtPosition<androidx.recyclerview.widget.RecyclerView.ViewHolder>(0, click())
            .let {
                onView(withId(R.id.our_mission_item_list_recycler_view)).perform(it)
            }
        return this
    }
}