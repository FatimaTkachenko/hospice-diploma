package ru.edu.qamid.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import io.qameta.allure.kotlin.Step
import ru.edu.qamid.R

/**
 * Page Object главного экрана приложения (News).
 */
class MainPage {

    @Step("Проверить, что главный экран отображается")
    fun checkMainScreenIsDisplayed(): MainPage {
        onView(withId(R.id.main_menu_image_button)).check(matches(isDisplayed()))
        onView(withId(R.id.our_mission_image_button)).check(matches(isDisplayed()))
        onView(withId(R.id.authorization_image_button)).check(matches(isDisplayed()))
        return this
    }

    @Step("Проверить, что кнопка ALL NEWS отображается")
    fun checkAllNewsButtonIsDisplayed(): MainPage {
        onView(withId(R.id.all_news_text_view)).check(matches(isDisplayed()))
        return this
    }

    @Step("Нажать ALL NEWS")
    fun clickAllNews(): MainPage {
        onView(withId(R.id.all_news_text_view)).perform(click())
        return this
    }

    @Step("Нажать кнопку Our Mission")
    fun clickOurMission(): MainPage {
        onView(withId(R.id.our_mission_image_button)).perform(click())
        return this
    }

    @Step("Нажать кнопку профиля (Authorization)")
    fun clickAuthorization(): MainPage {
        onView(withId(R.id.authorization_image_button)).perform(click())
        return this
    }

    @Step("Нажать кнопку главного меню")
    fun clickMainMenu(): MainPage {
        onView(withId(R.id.main_menu_image_button)).perform(click())
        return this
    }

    @Step("Проверить, что список новостей отображается")
    fun checkFirstNewsCardIsDisplayed(): MainPage {
        onView(withId(R.id.news_list_recycler_view)).check(matches(isDisplayed()))
        return this
    }
}