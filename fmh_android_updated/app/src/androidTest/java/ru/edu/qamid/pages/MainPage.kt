package ru.edu.qamid.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import ru.edu.qamid.R

/**
 * Page Object главного экрана приложения (News).
 * Содержит методы для навигации по приложению после авторизации.
 */
class MainPage {

    fun checkMainScreenIsDisplayed(): MainPage {
        onView(withId(R.id.main_menu_image_button)).check(matches(isDisplayed()))
        onView(withId(R.id.our_mission_image_button)).check(matches(isDisplayed()))
        onView(withId(R.id.authorization_image_button)).check(matches(isDisplayed()))
        return this
    }

    fun checkAllNewsButtonIsDisplayed(): MainPage {
        onView(withId(R.id.all_news_text_view)).check(matches(isDisplayed()))
        return this
    }

    fun clickAllNews(): MainPage {
        onView(withId(R.id.all_news_text_view)).perform(click())
        return this
    }

    fun clickOurMission(): MainPage {
        onView(withId(R.id.our_mission_image_button)).perform(click())
        return this
    }

    fun clickAuthorization(): MainPage {
        onView(withId(R.id.authorization_image_button)).perform(click())
        return this
    }

    fun clickMainMenu(): MainPage {
        onView(withId(R.id.main_menu_image_button)).perform(click())
        return this
    }

    fun checkFirstNewsCardIsDisplayed(): MainPage {
        onView(withId(R.id.news_list_recycler_view)).check(matches(isDisplayed()))
        return this
    }

    /**
     * Полный сценарий выхода из приложения:
     * открыть меню профиля → нажать Log out.
     */
    fun logout(): MainPage {
        clickAuthorization()
        onView(withText("Log out")).perform(click())
        return this
    }
}