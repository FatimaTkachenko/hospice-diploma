package ru.edu.qamid.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import io.qameta.allure.kotlin.Step
import ru.edu.qamid.R

/**
 * Page Object экрана "Our Mission" (цитаты).
 */
class OurMissionPage {

    @Step("Проверить, что заголовок 'Love is all' отображается")
    fun checkTitleIsDisplayed(): OurMissionPage {
        onView(withText("Love is all")).check(matches(isDisplayed()))
        return this
    }

    @Step("Проверить, что список цитат отображается")
    fun checkQuoteListIsDisplayed(): OurMissionPage {
        onView(withId(R.id.our_mission_item_list_recycler_view))
            .check(matches(isDisplayed()))
        return this
    }
}