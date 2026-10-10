package ru.edu.qamid.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import io.qameta.allure.kotlin.Allure
import ru.edu.qamid.R

/**
 * Page Object экрана "Our Mission" (цитаты).
 */
class OurMissionPage {

    private val titleText: ViewInteraction = onView(withText("Love is all"))
    private val quoteList: ViewInteraction = onView(withId(R.id.our_mission_item_list_recycler_view))

    fun checkTitleIsDisplayed(): OurMissionPage {
        Allure.step("Проверить, что заголовок 'Love is all' отображается") {
            titleText.check(matches(isDisplayed()))
        }
        return this
    }

    fun checkQuoteListIsDisplayed(): OurMissionPage {
        Allure.step("Проверить, что список цитат отображается") {
            quoteList.check(matches(isDisplayed()))
        }
        return this
    }
}