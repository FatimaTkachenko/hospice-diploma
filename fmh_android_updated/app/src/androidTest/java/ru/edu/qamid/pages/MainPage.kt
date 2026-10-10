package ru.edu.qamid.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import io.qameta.allure.kotlin.Allure
import ru.edu.qamid.R

/**
 * Page Object главного экрана приложения (News).
 */
class MainPage {

    private val mainMenuButton: ViewInteraction = onView(withId(R.id.main_menu_image_button))
    private val ourMissionButton: ViewInteraction = onView(withId(R.id.our_mission_image_button))
    private val authorizationButton: ViewInteraction = onView(withId(R.id.authorization_image_button))
    private val allNewsButton: ViewInteraction = onView(withId(R.id.all_news_text_view))
    private val newsRecyclerView: ViewInteraction = onView(withId(R.id.news_list_recycler_view))

    fun checkMainScreenIsDisplayed(): MainPage {
        Allure.step("Проверить, что главный экран отображается") {
            mainMenuButton.check(matches(isDisplayed()))
            ourMissionButton.check(matches(isDisplayed()))
            authorizationButton.check(matches(isDisplayed()))
        }
        return this
    }

    fun checkAllNewsButtonIsDisplayed(): MainPage {
        Allure.step("Проверить, что кнопка ALL NEWS отображается") {
            allNewsButton.check(matches(isDisplayed()))
        }
        return this
    }

    fun clickAllNews(): MainPage {
        Allure.step("Нажать ALL NEWS") {
            allNewsButton.perform(click())
        }
        return this
    }

    fun clickOurMission(): MainPage {
        Allure.step("Нажать кнопку Our Mission") {
            ourMissionButton.perform(click())
        }
        return this
    }

    fun clickAuthorization(): MainPage {
        Allure.step("Нажать кнопку профиля (Authorization)") {
            authorizationButton.perform(click())
        }
        return this
    }

    fun clickMainMenu(): MainPage {
        Allure.step("Нажать кнопку главного меню") {
            mainMenuButton.perform(click())
        }
        return this
    }

    fun checkFirstNewsCardIsDisplayed(): MainPage {
        Allure.step("Проверить, что список новостей отображается") {
            newsRecyclerView.check(matches(isDisplayed()))
        }
        return this
    }
}